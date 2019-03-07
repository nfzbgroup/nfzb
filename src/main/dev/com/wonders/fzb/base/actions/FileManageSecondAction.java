package com.wonders.fzb.base.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件管理
 * 有文件上传、下载操作的Action应继承此action
 * @author ZSW
 */
public class FileManageSecondAction extends FileManageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1302815249673748131L;

	// 注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File uploadFileSecond;

	// 提交过来的file的名字
	private String uploadFileSecondName;

	protected byte[] uploadSecond() {
		// 不是空文件
		if (null != uploadFileSecond && uploadFileSecond.length() > 0) {
			FileInputStream fis = null;
			byte[] bFile = null;
			try {
				bFile = new byte[(int) uploadFileSecond.length()];
				fis = new FileInputStream(uploadFileSecond);
				fis.read(bFile);
				// System.out.println(uploadFile.length() + "," + bFile.length);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					bFile.clone();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return bFile;
		}
		return null;
	}

 

	 


	public String getUploadFileSecondName() {
		return uploadFileSecondName;
	}






	public void setUploadFileSecondName(String uploadFileSecondName) {
		this.uploadFileSecondName = uploadFileSecondName;
	}






	public File getUploadFileSecond() {
		return uploadFileSecond;
	}

	public void setUploadFileSecond(File uploadFileSecond) {
		this.uploadFileSecond = uploadFileSecond;
	}

}
