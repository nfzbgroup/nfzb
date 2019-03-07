package com.wonders.fzb.platform.compose;

import java.util.*;

import com.wonders.fzb.platform.services.ComposeServer;


public class ComposerImp implements Composer {

    protected HashMap<String,String> _docTypeList ;

    public ComposerImp(){
        _docTypeList = new HashMap<String,String>() ;
    }
    /**
     * 根据文件类型得到对应的模板文件
     * @param docType 文件类型
     * @throws ComposeException
     */
    public byte[] getDocTemplate(String docId, String docType , String rootPath,DocProperties properties) throws ComposeException{
        return null;
    }

    /**
     * 保存文件s
     * @param userId 用户标识
     * @param docId 文档标识
     * @param versionId 版本号
     * @param fileContent 文件内
     * @throws ComposeException
     */
     public String save(String userId,String docId,String versionId,DocProperties properties,byte[] fileContent) throws ComposeException{
        return null;
    }

    /**
     * 初始化时注册missive composer<br>
     * @throws MessageException
     */
    public void register(ComposeServer composerServer) throws ComposeException{
        try{
            Iterator<String> iterator = _docTypeList.keySet().iterator();
            while(iterator.hasNext()){
                composerServer.registerComposer((String)(iterator.next()),this);
//            	HashMap<String,Composer> regList = composerServer.getRegList();
//                if(regList==null)
//            		regList = new HashMap<String,Composer>();
//                regList.put((String)(iterator.next()),this);
//                composerServer.setRegList(regList);
            }
        }catch(Exception e){
            throw new ComposeException(e);
        }
    }

    /**
     * 根据文件标识得到文件内容
     * @param docId 文件标识
     * @param processor processor实例
     * @throws ComposeException
     */
    public byte[] getDocFile(String docId,String userId,String versionId,DocProperties properties) throws ComposeException{
        return null;
    }

    public DocProperties getDocProperties(String docId,DocProperties properties) throws ComposeException{
        return null;
    }

    public void setDocProperties(String docId, String userId,DocProperties properties) throws ComposeException{
    }
}