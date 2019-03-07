package com.wonders.fzb.legislate.services.impl;

import java.io.FileInputStream;
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
import com.wonders.fzb.legislate.beans.TeamOpinion;
import com.wonders.fzb.legislate.services.TeamOpinionInFormComposer;
import com.wonders.fzb.legislate.services.TeamOpinionService;


@Service("teamOpinionInFormComposer")
@Transactional
public class TeamOpinionInFormComposerImpl extends ComposerImp implements TeamOpinionInFormComposer{

	@Autowired
    private CodeItemService codeItemService;
	@Autowired
	TeamOpinionService teamOpinionService;

	
    public TeamOpinionInFormComposerImpl(){
     
    }

	/**
	 * 根据文件类型得到对应的模板文件
	 */
	public byte[] getDocTemplate(String docId, String docType, String rootPath,DocProperties properties) throws ComposeException {
		System.out.println("getDocTemplate");
		try {
			byte[] buf = null;
			String fileName = rootPath + (String) _docTypeList.get(docType);
			FileInputStream fis = new FileInputStream(fileName);
			buf = new byte[fis.available()];
			fis.read(buf);
			return buf;
		} catch (Exception e) {
			System.out.println("DraftFbdComposer.getDocTemplate() error: "+ e.getMessage());
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
		 
		 System.out.println("save");
		try {
			TeamOpinion teamOpinion = new TeamOpinion();
			teamOpinion = teamOpinionService.findById(docId);
			if(teamOpinion==null){
				return null;
			}else{
				teamOpinion.setOpinionBlob(fileContent);
				teamOpinionService.saveOrUpdate(teamOpinion);
			}
			
			return null;
		} catch (Exception e) {
			System.out.println("DraftFbdComposer.save() error: " + e.getMessage());
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
		 System.out.println("getDocFile");
		byte[] ret = null;
		try {
			if(docId != null && !"".equals(docId)){
				TeamOpinion teamOpinion = teamOpinionService.findById(docId);
				ret = teamOpinion.getOpinionBlob();
			}
		} catch (Exception e) {
			System.out.println("DraftFbdComposer.save() error: " + e.getMessage());
			throw new ComposeException(e);
		}
		return ret;
		

	}

	public DocProperties getDocProperties(String docId , DocProperties properties )throws ComposeException {
		System.out.println("getDocProperties");
		DocProperties ret = new DocProperties();
		try {
/*			DraftTask draftTask = draftTaskService.findById(docId);
			Calendar c = Calendar.getInstance();
			c.setTime(draftTask.getTaskTime());
	        ret.put("RECEIVE_DAY", String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
	        ret.put("RECEIVE_MONTH",String.valueOf(c.get(Calendar.MONTH) + 1));
	        ret.put("RECEIVE_YEAR",String.valueOf(c.get(Calendar.YEAR)) );
	        ret.put("TEAM_NAME", draftTask.getDraft().getTeamName());
	        ret.put("DRAFT_NAME",draftTask.getDraft().getDraftName());
	        Fbd fbd = draftTask.getDraft().getFbd();
	        if(fbd != null){
	        	ret.put("FBD_ID", fbd.getFbdId());
	        	if(fbd.getProcessTime() != null){
	        		c.setTime(fbd.getProcessTime());
	 	       		ret.put("PROCESS_DAY", String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
	 	       		ret.put("PROCESS_MONTH",String.valueOf(c.get(Calendar.MONTH) + 1));
	 	       		ret.put("PROCESS_YEAR",String.valueOf(c.get(Calendar.YEAR)) );
	        	}
	        }*/
	        
		} catch (Exception e) {
			System.out.println("DraftFbdComposer.getDocProterties() error: "+ e.getMessage());
			throw new ComposeException(e);
		}
		return ret;
	}

	public void setDocProperties(String docId,String userId, DocProperties properties)throws ComposeException {
		System.out.println("setDocProperties");
		try {
				
		} catch (Exception e) {
			System.out.println("DraftFbdComposer.setDocProterties() error: "+ e.getMessage());
			throw new ComposeException(e);
		}

	}

	/**
	 * 初始化时注册report composer<br>
	 * 
	 * @throws ComposeException
	 */
	public void register(ComposeServer composerServer) throws ComposeException {
		System.out.println("register");
		try {
			CodeItem codeBase = codeItemService.getCodeItemByItemName(ComposeConst.PLATFORM_BASE_CODE,ComposeConst.CODE_ID_COMPOSE);
			codeBase = codeItemService.getCodeItemByItemName(codeBase.getStTid(), "单位意见内部表单Composer");
			if (codeBase == null)
				throw new ComposeException("error:platform configure data lost.");

			List<CodeItem> docTypeItemList = codeItemService.getChildItems(codeBase.getStTid());

			for (int i = 0; i < docTypeItemList.size(); i++) {
				CodeItem docTypeItem = (CodeItem) docTypeItemList.get(i);
				_docTypeList.put(docTypeItem.getStName(),docTypeItem.getStAppName());
			}
			super.register(composerServer);

		} catch (Exception e) {
			System.out.println("DraftFbdComposer.register() error: " + e.getMessage());
			throw new ComposeException(e);
		}
	}
}
