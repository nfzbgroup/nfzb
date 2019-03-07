package com.wonders.fzb.platform.services;

import com.wonders.fzb.platform.compose.ComposeException;
import com.wonders.fzb.platform.compose.Composer;

public interface ComposeServer{
	
    /**
     * 根据文档类型得到相应的composer<br>
     * @param composeType compose类型
     * @param composer Composer实例
     * @throws MessageException
     */
    public Composer getComposer(String docType)  throws ComposeException;

    /**
     * 供Composer初始化时调用<br>
     * @param composeType 消息类型
     * @param composer composer实例
     * @throws ComposeException
     */
    public void registerComposer(String docType,Composer composer) throws ComposeException;

    /**
     * 初始化WegovComposeServer,读取平台的Composer信息
     * @throws MessageException
     */
    public void load() throws ComposeException;


}