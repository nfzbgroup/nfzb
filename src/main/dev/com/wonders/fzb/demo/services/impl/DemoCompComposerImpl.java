package com.wonders.fzb.demo.services.impl;

import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.demo.beans.ComposerDemo;
import com.wonders.fzb.demo.services.ComposerDemoService;
import com.wonders.fzb.demo.services.DemoCompComposer;
import com.wonders.fzb.framework.beans.CodeItem;
import com.wonders.fzb.framework.compose.ComposeConst;
import com.wonders.fzb.framework.compose.ComposeException;
import com.wonders.fzb.framework.compose.ComposerImp;
import com.wonders.fzb.framework.compose.DocProperties;
import com.wonders.fzb.framework.services.CodeItemService;
import com.wonders.fzb.framework.services.ComposeServer;
import com.wonders.fzb.framework.services.UserInfoService;


@Service("demoCompComposer")
@Transactional
public class DemoCompComposerImpl extends ComposerImp implements DemoCompComposer{

	@Autowired
    private CodeItemService codeItemService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ComposerDemoService demoService;
	
    public DemoCompComposerImpl(){
     
    }

	/**
	 * 根据文件类型得到对应的模板文件
	 */
	public byte[] getDocTemplate(String docId, String docType, String rootPath,DocProperties properties) throws ComposeException {
		try {
			byte[] buf = null;
			String fileName = rootPath + (String) _docTypeList.get(docType);
			FileInputStream fis = new FileInputStream(fileName);
			buf = new byte[fis.available()];
			fis.read(buf);
			return buf;
		} catch (Exception e) {
			System.out.println("DemoCompComposer.getDocTemplate() error: "+ e.getMessage());
			throw new ComposeException(e);
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param userId
	 *            用户标识
	 * @param docId
	 *            文档标识，null表示添加新文档
	 * @param fileContent
	 *            文件内容
	 * @param fileFormat
	 *            文件格式
	 * @throws MessageException
	 */
	 public String save(String userId,String docId,String versionId,DocProperties properties,byte[] fileContent) throws ComposeException {
		try {
			ComposerDemo demo = new ComposerDemo();
			demo = demoService.findById("test_1");
			if(demo==null){
				demo = new ComposerDemo();
				demo.setComposerId("test_1");
				demo.setComposerDate(new java.util.Date());
				demo.setComposerBlob(fileContent);
				demo.setComposerType("COMP_DEMO");
				demoService.add(demo);
			}else{
				demo.setComposerBlob(fileContent);
				demoService.saveOrUpdate(demo);
			}
			return null;
		} catch (Exception e) {
			System.out.println("DemoCompComposer.save() error: " + e.getMessage());
			throw new ComposeException(e);
		}
	}

	/**
	 * 根据文件标识得到文件内容
	 * 
	 * @param docId
	 *            文件标识
	 * @param processor
	 *            processor实例
	 * @throws MessageException
	 */
	 public byte[] getDocFile(String docId,String userId,String versionId,DocProperties properties)  throws ComposeException {
		byte[] ret = null;
		try {
			if(docId != null && !"".equals(docId)){
				ComposerDemo demo= demoService.findById(docId);
				ret = demo.getComposerBlob();
			}
		} catch (Exception e) {
			System.out.println("DemoCompComposer.save() error: " + e.getMessage());
			throw new ComposeException(e);
		}
		return ret;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public DocProperties getDocProperties(String docId , DocProperties properties )throws ComposeException {
		DocProperties ret = new DocProperties();
		try {
	        ret.put("DEMO_CONTENT", "测试内容");
	        
		} catch (Exception e) {
			System.out.println("DemoCompComposer.getDocProterties() error: "+ e.getMessage());
			throw new ComposeException(e);
		}
		return ret;
	}

	public void setDocProperties(String docId,String userId, DocProperties properties)throws ComposeException {
		try {
			// OutPlan info = outPlanService.findById(docId);
			// info.setPlanName(properties.get("PLAN_NAME"));
			// info.setPlanDay(properties.get("PLAN_DAY"));
			// info.setSendRange(properties.get("SEND_RANGE"));
			// outPlanService.saveOrUpdate(info);
				
		} catch (Exception e) {
			System.out.println("DemoCompComposer.setDocProterties() error: "+ e.getMessage());
			throw new ComposeException(e);
		}

	}

	/**
	 * 初始化时注册report composer<br>
	 * 
	 * @throws ComposeException
	 */
	public void register(ComposeServer composerServer) throws ComposeException {
		try {
			CodeItem codeBase = codeItemService.getCodeItemByItemName(ComposeConst.PLATFORM_BASE_CODE,ComposeConst.CODE_ID_COMPOSE);
			codeBase = codeItemService.getCodeItemByItemName(codeBase.getStTid(), "测试Composer");
			if (codeBase == null)
				throw new ComposeException("error:platform configure data lost.");

			List<CodeItem> docTypeItemList = codeItemService.getChildItems(codeBase.getStTid());

			for (int i = 0; i < docTypeItemList.size(); i++) {
				CodeItem docTypeItem = (CodeItem) docTypeItemList.get(i);
				_docTypeList.put(docTypeItem.getStName(),docTypeItem.getStAppName());
			}
			super.register(composerServer);

		} catch (Exception e) {
			System.out.println("DemoCompComposer.register() error: " + e.getMessage());
			throw new ComposeException(e);
		}
	}
}
