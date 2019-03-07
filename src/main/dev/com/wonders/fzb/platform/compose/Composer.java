package com.wonders.fzb.platform.compose;

import com.wonders.fzb.platform.services.ComposeServer;

/**
 * Composer interface
 */
public interface Composer {

    /**
     * 得到对应的模板文件
     * @param docId 文档标识
     * @param docType 文件类型
     * @param  dps  页面传过来的参数/值 (参数名称必须大写)
     * @throws ComposeException
     */
    public byte[] getDocTemplate(String docId,String docType , String rootPath,DocProperties properties) throws ComposeException;

    /**
     * 保存文件
     * @param userId 用户标识
     * @param docId 文档标识
     * @param versionId 版本号
     * @param  dps  页面传过来的参数/值 (参数名称必须大写)
     * @param fileContent 文件内容
     * @throws MessageException
     */
    public String save(String userId,String docId,String versionId,DocProperties properties,byte[] fileContent) throws ComposeException;

    /**
     * 初始化时注册composer<br>
     * @param messageType 消息类型
     * @param processor processor实例
     * @throws MessageException
     */
    public void register(ComposeServer composeServer) throws ComposeException;

    /**
     * 得到文件内容
     * @param docId 文件标识
     * @param userId 用户标识
     * @param versionid 版本号
     * @param  dps  页面传过来的参数/值 (参数名称必须大写)
     * @throws MessageException
     */
    public byte[] getDocFile(String docId,String userId,String versionId,DocProperties properties) throws ComposeException;
    /**
     * 得到文件要素（用于替换模板）
     * @param docId 文件标识
     * @param  dps  页面传过来的参数/值 (参数名称必须大写)
     * @throws MessageException
     */
    public DocProperties getDocProperties(String docId,DocProperties properties) throws ComposeException;
    /**
     * 设置文件要素
     * @param docId 文件标识
     * @param  dps  页面传过来的参数/值 (参数名称必须大写)
     * @throws MessageException
     */
    public void setDocProperties(String docId, String userId,DocProperties properties) throws ComposeException;

}