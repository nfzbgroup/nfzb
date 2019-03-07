package com.wonders.fzb.base.logging.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wonders.fzb.base.consts.LogConst;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logging {
	String value() default "";
	String ctx() default "应用日常运行日志记录";
	LogConst.Rank rank() default LogConst.Rank.NORMAL;
	LogConst.Category category() default LogConst.Category.RUNTIME;
}
