package com.wonders.fzb.legislation.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDeal;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDealService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.io.*;
import java.util.Date;
import java.util.Random;

/**
 * Created by zq008 on 2019/3/6.
 */
@Namespace("/file")
@Controller
@Scope("prototype")
public class FileAction extends BaseAction {

    @Autowired
    @Qualifier("legislationFilesService")
    private LegislationFilesService legislationFilesService;

    @Autowired
    @Qualifier("legislationProcessTaskService")
    private LegislationProcessTaskService legislationProcessTaskService;

    @Autowired
    @Qualifier("legislationProcessDocService")
    private LegislationProcessDocService legislationProcessDocService;

    @Autowired
    @Qualifier("legislationProcessDealService")
    private LegislationProcessDealService legislationProcessDealService;

    private File upload;

    private String uploadContentType;

    private String uploadFileName;

    private String filePath="D:/idea/nfzb-github/WebRoot/legislation/file";

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    @Action(value = "upload")
    public void upload() throws FzbDaoException, IOException {
        JSONObject jsonObject=new JSONObject();
        String path =filePath;
        String fileName=getCode()+"."+uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        File file = new File(path, fileName);
        FileUtils.copyFile(upload, file);



        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userId = currentPerson.getUserId();
        String userName = currentPerson.getName();

        LegislationFiles legislationFiles = new LegislationFiles();
        legislationFiles.setStFileUrl(path+"/"+fileName);
        legislationFiles.setStFormat(uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1));
        legislationFiles.setStOwnerId(userId);
        legislationFiles.setStOwnerName(userName);
        legislationFiles.setDtPubDate(new Date());
        legislationFiles.setStTitle(uploadFileName);
        legislationFiles.setStNodeId(request.getParameter("stNodeId"));
        legislationFiles.setStSampleId(request.getParameter("stSampleId"));

        String fileId = legislationFilesService.addObj(legislationFiles);

        jsonObject.put("url",path+"/"+fileName);
        jsonObject.put("name",uploadFileName);
        jsonObject.put("fileName",fileName);
        jsonObject.put("fileId",fileId);
        jsonObject.put("success",true);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @Action(value = "addOpinion")
    public void addOpinion() throws FzbDaoException, IOException {
        JSONObject jsonObject=new JSONObject();
        String path =filePath;
        String fileName=getCode()+"."+uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        File file = new File(path, fileName);
        FileUtils.copyFile(upload, file);

        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userId = currentPerson.getUserId();
        String userName = currentPerson.getName();

        String stTaskId=request.getParameter("stTaskId");
        LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stTaskId);
        LegislationFiles legislationFiles = new LegislationFiles();
        legislationFiles.setStFileUrl(path+"/"+fileName);
        legislationFiles.setStFormat(uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1));
        legislationFiles.setStOwnerId(userId);
        legislationFiles.setStOwnerName(userName);
        legislationFiles.setDtPubDate(new Date());
        legislationFiles.setStTitle(uploadFileName);
        legislationFiles.setStNodeId(legislationProcessTask.getStNodeId());

        String fileId = legislationFilesService.addObj(legislationFiles);
        legislationProcessTask.setStComment2(fileId);
        legislationProcessTask.setDtBakDate(new Date());
        legislationProcessTask.setStTaskStatus("DONE");
        legislationProcessTaskService.update(legislationProcessTask);
        LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(legislationProcessTask.getStDocId());
        LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
        legislationProcessDeal.setStDocId(legislationProcessDoc.getStDocId());
        legislationProcessDeal.setStActionId(legislationProcessTask.getStNodeId());
        legislationProcessDeal.setStActionName(legislationProcessTask.getStNodeName());
        legislationProcessDeal.setStUserId(userId);
        legislationProcessDeal.setStUserName(userName);
        legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
        legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
        legislationProcessDeal.setDtDealDate(new Date());
        legislationProcessDealService.add(legislationProcessDeal);
        jsonObject.put("url",path+"/"+fileName);
        jsonObject.put("name",uploadFileName);
        jsonObject.put("time",DateFormatUtils.format(legislationProcessTask.getDtBakDate(),"yyyy-MM-dd HH:mm:ss"));
        jsonObject.put("success",true);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @Action(value = "deleteAttach")
    public void deleteAttach() throws Exception {
        String fileId = request.getParameter("fileId");
        LegislationFiles legislationFiles = new LegislationFiles();
        legislationFiles.setStFileId(fileId);
        legislationFilesService.delete(legislationFiles);
    }

    @Action(value = "downloadAttach")
    public void downloadAttach() throws Exception{
        String name=request.getParameter("name");
        String url=request.getParameter("url");
        request.setCharacterEncoding("UTF-8");
        //第一步：设置响应类型
        response.setContentType("application/force-download");//应用程序强制下载
        //第二读取文件
        InputStream in = new FileInputStream(url);
        //设置响应头，对文件进行url编码
        name = new String(name.getBytes("UTF-8"),"ISO-8859-1");
        response.setHeader("Content-Disposition",  String.format("attachment; filename=\"%s\"", name));
        response.setContentLength(in.available());
        response.setCharacterEncoding("UTF-8");
        //第三步：老套路，开始copy
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while((len = in.read(b))!=-1){
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

    public static String getCode(){
        return DateFormatUtils.format(new Date(),"yyyyMMddHHmmss")+getFourRandom();
    }

    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    private static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }
        return fourRandom;
    }

}
