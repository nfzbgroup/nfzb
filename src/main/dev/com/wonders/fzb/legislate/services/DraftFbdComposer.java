package com.wonders.fzb.legislate.services;

import com.wonders.fzb.framework.compose.ComposeException;
import com.wonders.fzb.framework.compose.Composer;
import com.wonders.fzb.framework.compose.DocProperties;
import com.wonders.fzb.framework.services.ComposeServer;

public interface DraftFbdComposer extends Composer{

    /**
     * 根据文件类型得到对应的模板文件
     * @param docType 文件类型
     * @throws ComposeException
     */
    public byte[] getDocTemplate(String docId , String docType , String docRoot ,DocProperties properties) throws ComposeException;

    /**
     * 保存文件
     * @param userId 用户标识
     * @param docId 文档标识，0表示添加新文档,docId=0:直接使用公文之星创建新文件
     * @param fileContent 文件内容
     * @param versionId 版本号  -1：保存新版本 ；其他为要更新的版本号
     * @param fileFormat 文件格式
     * @throws ComposeException
     */
    public String save(String userId,String docId,String versionId,DocProperties properties,byte[] fileContent) throws ComposeException;

    /**
     * 根据文件标识得到文件内容
     * @param userId 用户标识
     * @param docId 文档标识，null表示添加新文档
     * @param versionId 版本号
     * @param opType 操作类型
     * @param docFormat 文件格式
     * @throws ComposeException
     */
    public byte[] getDocFile(String docId,String userId,String versionId,DocProperties properties) throws ComposeException;

    public DocProperties getDocProperties(String docId , DocProperties properties ) throws ComposeException;

    public void setDocProperties(String docId,String userId, DocProperties properties) throws ComposeException;

    /**
     * 初始化时注册missive composer<br>
     * @throws MessageException
     */
    public void register(ComposeServer composerServer) throws ComposeException;
}
