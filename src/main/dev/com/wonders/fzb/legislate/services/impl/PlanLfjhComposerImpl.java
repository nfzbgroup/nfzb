package com.wonders.fzb.legislate.services.impl;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.framework.beans.CodeItem;
import com.wonders.fzb.framework.compose.ComposeConst;
import com.wonders.fzb.framework.compose.ComposeException;
import com.wonders.fzb.framework.compose.ComposerImp;
import com.wonders.fzb.framework.compose.DocProperties;
import com.wonders.fzb.framework.services.CodeItemService;
import com.wonders.fzb.framework.services.ComposeServer;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.Plan;
import com.wonders.fzb.legislate.services.FileRecordService;
import com.wonders.fzb.legislate.services.PlanLfjhComposer;
import com.wonders.fzb.legislate.services.PlanService;

@Service("planLfjhComposer")
@Transactional
public class PlanLfjhComposerImpl extends ComposerImp implements PlanLfjhComposer {

	@Autowired
    private CodeItemService codeItemService;
	
	@Autowired
	private FileRecordService fileRecordService;

	@Autowired
	PlanService planService;

	public PlanLfjhComposerImpl() {
	}

	/**
	 * 根据文件类型得到对应的模板文件
	 */
	public byte[] getDocTemplate(String docId, String docType, String rootPath, DocProperties properties) throws ComposeException {
		try {
			byte[] buf = null;
			String fileName = rootPath + (String) _docTypeList.get(docType);
			FileInputStream fis = new FileInputStream(fileName);
			buf = new byte[fis.available()];
			fis.read(buf);
			return buf;
		} catch (Exception e) {
			System.out.println("PlanLfjhComposer.getDocTemplate() error: " + e.getMessage());
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
	public String save(String userId, String docId, String versionId, DocProperties properties, byte[] fileContent) throws ComposeException {
		try {
			FileRecord fileRecord = fileRecordService.findById(docId);
			Plan plan = planService.findById(docId);
			if (fileRecord == null) {
				fileRecord = new FileRecord();
				fileRecord.setActivityType("FBD");
				fileRecord.setBizType("立法计划");
				fileRecord.setCreateTime(new Date());
				fileRecord.setFileRecordId(docId);
				fileRecord.setFileName(plan.getPlanName() + "(立法计划项目表).doc");
				fileRecord.setCreatorId("-");
				fileRecord.setCreatorName("-");
				fileRecord.setOutId(docId);
				fileRecord.setFileContent(fileContent);
				fileRecordService.add(fileRecord);
			} else {
				fileRecord.setFileContent(fileContent);
				fileRecordService.update(fileRecord);
			}
			return null;
		} catch (Exception e) {
			System.out.println("PlanLfjhComposer.save() error: " + e.getMessage());
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
	public byte[] getDocFile(String docId, String userId, String versionId, DocProperties properties) throws ComposeException {
		byte[] ret = null;
		try {
			if (docId != null && !"".equals(docId)) {
				FileRecord fileRecord = fileRecordService.findById(docId);
				if (fileRecord != null) {
					return fileRecord.getFileContent();
				}
			}
		} catch (Exception e) {
			System.out.println("PlanLfjhComposer.save() error: " + e.getMessage());
			throw new ComposeException(e);
		}
		return ret;
	}

	public DocProperties getDocProperties(String docId, DocProperties properties) throws ComposeException {
		DocProperties ret = new DocProperties();
		try {
			Plan plan = planService.findById(docId);
			if(plan.getPlanName()!=null)
				ret.put("planName", plan.getPlanName());
			if(plan.getLexSuperior()!=null)
				ret.put("lexSuperior", plan.getLexSuperior());
			if(plan.getReason()!=null)
				ret.put("reason", plan.getReason());
			if(plan.getProgress()!=null)
				ret.put("progress", plan.getProgress());
			if("正式项目".equals(plan.getProjectType())){
				if(plan.getRemark()!=null)
					ret.put("remark", "拟于"+plan.getSendYear()+"年"+plan.getSendMonth()+"月前报送规章草案\r\n"+plan.getRemark());
			}else{
				if(plan.getRemark()!=null)
					ret.put("remark", plan.getRemark());
			}
			if(plan.getContacts()!=null)
				ret.put("contacts", plan.getContacts());
			if(plan.getTelephone()!=null)
				ret.put("telephone", plan.getTelephone());
			if(plan.getPlanType()!=null){
				if("制定项目".equals(plan.getPlanType())){
					ret.put("projectType1", "√");
				}
				if("修订项目".equals(plan.getPlanType())){
					ret.put("projectType2", "√");
				}
				if("废止项目".equals(plan.getPlanType())){
					ret.put("projectType3", "√");
				}
			}
			if(plan.getProjectType()!=null){
				if("正式项目".equals(plan.getProjectType())){
					ret.put("planType1", "√");
				}
				if("预备项目".equals(plan.getProjectType())){
					ret.put("planType2", "√");
				}
				if("调研项目".equals(plan.getProjectType())){
					ret.put("planType3", "√");
				}
			}
			if(plan.getCreatorName()!=null) {
				ret.put("unitName", plan.getCreatorName());
			}
		} catch (Exception e) {
			System.out.println("PlanLfjhComposer.getDocProterties() error: " + e.getMessage());
			throw new ComposeException(e);
		}
		return ret;
	}
	
	public void setDocProperties(String docId,String userId, DocProperties properties)throws ComposeException {
		try {
				
		} catch (Exception e) {
			System.out.println("PlanLfjhComposer.setDocProterties() error: "+ e.getMessage());
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
			codeBase = codeItemService.getCodeItemByItemName(codeBase.getStTid(), "规章制定计划项目表Composer");
			if (codeBase == null)
				throw new ComposeException("error:platform configure data lost.");

			List<CodeItem> docTypeItemList = codeItemService.getChildItems(codeBase.getStTid());

			for (int i = 0; i < docTypeItemList.size(); i++) {
				CodeItem docTypeItem = (CodeItem) docTypeItemList.get(i);
				_docTypeList.put(docTypeItem.getStName(),docTypeItem.getStAppName());
			}
			super.register(composerServer);

		} catch (Exception e) {
			System.out.println("PlanLfjhComposer.register() error: " + e.getMessage());
			throw new ComposeException(e);
		}
	}
}
