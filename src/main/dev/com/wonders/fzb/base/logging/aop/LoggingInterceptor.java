package com.wonders.fzb.base.logging.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wonders.fzb.base.consts.LogConst;
import com.wonders.fzb.base.kit.StringKit;
import com.wonders.fzb.base.logging.annotations.Logging;
import com.wonders.fzb.base.logging.beans.LogInfo;
import com.wonders.fzb.base.logging.beans.Struct;
import com.wonders.fzb.base.logging.services.LoggingService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 日志切面
 * 
 * @author ZSW
 */
@Aspect
@Component
public class LoggingInterceptor {

	private JsonConfig cfg = new JsonConfig();

	private HttpServletRequest request;

	private HttpSession session;

	public LoggingInterceptor() {
		cfg.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
			private final String format = "yyyy-MM-dd HH:mm:ss";

			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				if (value == null)
					return "";
				if (value instanceof Date) {
					String str = new SimpleDateFormat(format).format((Date) value);
					return str;
				}
				return value.toString();
			}

			public Object processArrayValue(Object value, JsonConfig arg1) {
				return null;
			}
		});

	}

	@Autowired
	private LoggingService loggingService;

	/**
	 * 手动定义要记录日志的点
	 */
	@Pointcut("@annotation(com.wonders.fzb.base.logging.annotations.Logging)")
	private void customMethod() {
	}

	/**
	 * 默认监控所有功能下的Services
	 */
	@Pointcut("execution(* com.wonders.fzb.zfry.module.*.services.*.*(..))")
	private void defaultServicesMethod() {
	}

	@Pointcut("execution(* com.wonders.fzb.zfry.module.*.web.*.*(..))")
	private void webs() {
	}

	@Pointcut("execution(* com.wonders.fzb.zfry.module.*.actions.*.*(..))")
	private void actions() {
	}

	@Before("defaultServicesMethod()")
	public void beforeExcute(JoinPoint jp) {
		// 仅在第一次执行方法之前加载
		if (null == request && null != RequestContextHolder.getRequestAttributes()) {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			
			if (null == session) {
				session = request.getSession();
			}
		}
		
		// HttpServletRequest request =
		// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// HttpSession session = request.getSession();
		// System.out.println(session.getId());

		// TODO 前置校验
		// System.out.println("LongString:" + jp.toLongString());
		// System.out.println("ShortString:" + jp.toShortString());
		// System.out.println("Signature:"+jp.getSignature().getDeclaringTypeName()
		// + "," + jp.getSignature().getName());
		// System.out.println("Target:"+jp.getTarget());
		// System.out.println("Args:" +JSONArray.fromObject(jp.getArgs()));

		// System.out.println("前置通知:"+Arrays.toString(jp.getArgs()) + "," +
		// jp.getSignature().getDeclaringTypeName());
	}

	//@Around("defaultServicesMethod() || customMethod()")
	@Around("customMethod()")
	public Object aroundExcute(ProceedingJoinPoint pjp) throws Throwable {
		// TODO Skip excute method

		Long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		Long endTime = System.currentTimeMillis();
		LogInfo logInfo = analyzeAnnotation(pjp, result);
		if (null != logInfo) {
			logInfo.setCost(Integer.parseInt(endTime - startTime + ""));
			loggingService.log(logInfo);
		}
		return result;
	}

	@AfterReturning(pointcut = "defaultServicesMethod()", returning = "result")
	public void afterExcute(JoinPoint jp, Object result) {

	}

	@AfterThrowing(pointcut = "defaultServicesMethod()", throwing = "ex")
	public void whenError(JoinPoint jp, Exception ex) {
		StackTraceElement ste = ex.getStackTrace()[0];
		List<String> msgList = new ArrayList<String>();
		msgList.add("异常信息：" + ex.getMessage());
		msgList.add("异常类名：" + ste.getClassName());
		msgList.add("异常行号：" + ste.getLineNumber());
		msgList.add("异常函数：" + ste.getMethodName());
		msgList.add("抛出类名：" + jp.getTarget());
		msgList.add("抛出函数：" + jp.getSignature().getName());
		// String errorCode =UUID.randomUUID().toString();
		String errorCode = StringKit.convertDate(new Date(), "yyyyMMddHHmmssSSS");

		msgList.add("异常编号:" + errorCode);
		String errorDetails = StringKit.join(msgList, "，");

		if (null != request && null != request.getSession())
			request.setAttribute("errorCode", errorCode);

		LogInfo logInfo = new LogInfo();
		logInfo.setClassName(jp.getTarget().getClass().getPackage().getName() + "." + jp.getTarget().getClass().getSimpleName());
		logInfo.setMethodName(jp.getSignature().getName());
		List<Struct> args = new ArrayList<Struct>();
		for (Object obj : jp.getArgs()) {
			Struct struct = null;
			if (null != obj)
				struct = new Struct(obj.getClass().getPackage().getName(), obj.getClass().getSimpleName(), obj);
			else
				struct = new Struct("", "", "null");
			args.add(struct);
		}
		logInfo.setParams(JSONArray.fromObject(args, cfg).toString());

		logInfo.setDelegation(this.getClass().getName());
		logInfo.setTime(new Date());
		logInfo.setCtx(errorDetails);
		logInfo.setCategory(LogConst.Category.ERROR);
		logInfo.setRank(LogConst.Rank.IMPORTANT);
		loggingService.log(logInfo);
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public LogInfo analyzeAnnotation(JoinPoint joinPoint, Object result) throws Exception {
		try {
			String targetName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			Object[] arguments = joinPoint.getArgs();
			List<Struct> args = new ArrayList<Struct>();
			for (Object obj : arguments) {
				Struct struct = null;
				if (null != obj)
					struct = new Struct(obj.getClass().getPackage().getName(), obj.getClass().getSimpleName(), obj);
				else
					struct = new Struct("", "", "null");
				args.add(struct);
			}
			Class targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			LogInfo logInfo = new LogInfo();

			logInfo.setClassName(joinPoint.getTarget().getClass().getPackage().getName() + "." + joinPoint.getTarget().getClass().getSimpleName());
			logInfo.setMethodName(joinPoint.getSignature().getName());
			logInfo.setParams(JSONArray.fromObject(args, cfg).toString());
			logInfo.setDelegation(this.getClass().getName());
			logInfo.setTime(new Date());

			if (null != result) {
				if (result instanceof Collection) {
					List<Struct> results = new ArrayList<Struct>();
					for (Object obj : (Collection) result) {
						Struct struct = null;
						if (null != obj)
							struct = new Struct(obj.getClass().getPackage().getName(), obj.getClass().getSimpleName(), obj);
						else
							struct = new Struct("", "", "null");
						results.add(struct);
					}
					logInfo.setResult(JSONArray.fromObject(results, cfg).toString());
				} /*
					 * else if(result instanceof Map){ Struct struct = new
					 * Struct(result.getClass().getPackage().getName(),result.
					 * getClass().getSimpleName(),result);
					 * logInfo.setResult(JSONObject.fromObject(struct,
					 * cfg).toString()); }
					 */else {
					if (result instanceof Boolean || result instanceof Integer || result instanceof String || result instanceof Float || result instanceof Double || result instanceof Character)
						logInfo.setResult(result.toString());
					else {
						Struct struct = new Struct(result.getClass().getPackage().getName(), result.getClass().getSimpleName(), result);
						logInfo.setResult(JSONObject.fromObject(struct, cfg).toString());
					}
				}
			}

			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						Logging annotation = method.getAnnotation(Logging.class);
						if (annotation != null) {
							// 如果切点类上有注解则是手动记录
							logInfo.setMode(0);
							// TODO 按照注解中标注的设置
							logInfo.setCategory(annotation.category());
							logInfo.setRank(annotation.rank());
							logInfo.setCtx("".equals(annotation.value()) ? annotation.ctx() : annotation.value());

							if (!annotation.category().equals(LogConst.Category.LOGIN)) {
//								UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
//								logInfo.setUsr(currentPerson.account);
							}

							if (annotation.category().equals(LogConst.Category.DEBUG)) {
								System.out.println("[调试信息]:\t");
								System.out.println("\t\t类名：" + logInfo.getClassName());
								System.out.println("\t\t方法：" + logInfo.getMethodName());
								System.out.println("\t\t参数：" + logInfo.getParams());
								System.out.println("\t\t返回：" + logInfo.getResult());
							}
						} else {
							// 默认是运行日志
							logInfo.setCategory(LogConst.Category.RUNTIME);
							logInfo.setHost(StringKit.getIpAddr(request));

							// 默认级别是普通
							logInfo.setRank(LogConst.Rank.NORMAL);
							logInfo.setCtx("应用日常运行日志记录");

//							UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
//							if (null != currentPerson)
//								logInfo.setUsr(currentPerson.account);
						}
						break;
					}
				}
			}
			return logInfo;
		} catch (Exception e) {
			return null;
		}
	}
}
