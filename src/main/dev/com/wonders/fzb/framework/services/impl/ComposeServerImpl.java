package com.wonders.fzb.framework.services.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.framework.beans.CodeItem;
import com.wonders.fzb.framework.compose.ComposeConst;
import com.wonders.fzb.framework.compose.ComposeException;
import com.wonders.fzb.framework.compose.Composer;
import com.wonders.fzb.framework.services.CodeItemService;
import com.wonders.fzb.framework.services.ComposeServer;

@Service("composeServer")
@Transactional
public class ComposeServerImpl extends ApplicationObjectSupport implements ComposeServer{

//    private static WegovComposeServerImpl server = null;
//    private static int isLoad = 0;

    private HashMap<String,Composer> regList = null;
    
	@Autowired
    private CodeItemService codeItemService;

	/**
     * 根据文档类型得到相应的composer<br>
     * @param composeType compose类型
     * @param composer Composer实例
     * @throws MessageException
     */
    public Composer getComposer(String docType) throws ComposeException{
    	try{
	        if(regList==null)
	        	this.load();
	        return (Composer)this.regList.get(docType);
    	}catch(Exception e){
    		System.out.println("ComposeServer.getComposer("+docType+")  error . Ignore !");
    		throw new ComposeException(e);
    	}
     }

    /**
     * 供Composer初始化时调用<br>
     * @param composeType 消息类型
     * @param composer composer实例
     * @throws ComposeException
     */
    public void registerComposer(String docType,Composer composer) throws ComposeException{
        try{
        	if(regList==null)
        		regList = new HashMap<String,Composer>();
            regList.put(docType,composer);
        }catch(Exception e){
        	System.out.println("ComposeServerImp.registerComposer() error: " + e.getMessage());
            throw new ComposeException(e);
        }
    }

    /**
     * 初始化WegovComposeServer,读取平台的Composer信息
     * @throws MessageException
     */
    public void load() throws ComposeException{
        try{
        		regList = new HashMap<String,Composer>();
        		CodeItem codeBase = codeItemService.getCodeItemByItemName(ComposeConst.PLATFORM_BASE_CODE,ComposeConst.CODE_ID_COMPOSE);
                if(codeBase==null)
                    throw new ComposeException("error:platform configure data lost.");
                List<CodeItem> codeList  = codeItemService.getChildItems(codeBase.getStTid());
                ApplicationContext context = super.getApplicationContext();

                for(int i=0;i<codeList.size();i++){
                   try{
                     codeBase = (CodeItem)codeList.get(i);
                     String beanName = codeBase.getStAppName();
                     beanName = beanName.substring(beanName.lastIndexOf(".")+1);
                     beanName = beanName.substring(0,1).toLowerCase() + beanName.substring(1);
	                 Composer composer = (Composer)context.getBean(beanName);
	                 if(composer!=null)
	                    	 composer.register(this);
//	                     List<CodeItem> docTypeItemList  = codeItemService.getChildItems(codeBase.stTid);
//	                     for(int j = 0; j < docTypeItemList.size(); j++){
//	                         CodeItem docTypeItem = (CodeItem)docTypeItemList.get(j);
//	                         regList.put(docTypeItem.stName, composer);
//	                         //composerServer.registerComposer(docTypeItem.stName,this);
//	                     }
                   }catch(Exception e){
                	   System.out.println("Load \""+codeBase.getStAppName()+"\" Composer  error . Ignore !");
                   }
                }

        }catch(Exception e){
        	System.out.println("WegovComposeServer.load() error: " + e.getMessage());
            throw new ComposeException(e);
        }
    }
}