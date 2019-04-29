package com.wonders.fzb.base.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理
 * 有文件上传、下载操作的Action应继承此action
 * @author ZSW
 */
public class FileManageAction extends BaseAction {

	private static final long serialVersionUID = 8295015444447101210L;

	// 注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File uploadFile;
	private String uploadFileFileName;
	private List<File> uploads;
	private File proveFile;
	private String proveFileFileName;
	private File otherFile;
	private String otherFileFileName;
	private List<String> uploadsFileName; // 附件名称
	private List<String> uploadsContentType; // 附件类型
	private String savePath; // 附件保存的路径
	private List<String> fileNames; // 用户输入的名称

	// 注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	// private File uploadFileSecond;

	// 提交过来的file的名字
	// private String uploadFileSecondName;

	protected byte[] otherFile() {
		// 不是空文件
		if (null != otherFile && otherFile.length() > 0) {
			FileInputStream fis = null;
			byte[] bFile = null;
			try {
				bFile = new byte[(int) otherFile.length()];
				fis = new FileInputStream(otherFile);
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

	protected byte[] proveFile() {
		// 不是空文件
		if (null != proveFile && proveFile.length() > 0) {
			FileInputStream fis = null;
			byte[] bFile = null;
			try {
				bFile = new byte[(int) proveFile.length()];
				fis = new FileInputStream(proveFile);
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

	protected List<byte[]> uploads() {
		List<byte[]> bFiles = new ArrayList<byte[]>();
		// 不是空文件
		if (uploads != null) {
			for (int i = 0; i < uploads.size(); i++) {
				if (null != uploads.get(i) && uploads.get(i).length() > 0) {
					FileInputStream fis = null;
					byte[] bFile = null;
					try {

						if (getUploadFileFileName().indexOf(".asp") != -1 || getUploadFileFileName().indexOf(".jsp") != -1 || getUploadFileFileName().indexOf(".php") != -1 || getUploadFileFileName().indexOf(".sql") != -1) {
							throw new Exception("文件类型不符合要求！");
						}

						bFile = new byte[(int) uploads.get(i).length()];
						fis = new FileInputStream(uploads.get(i));
						fis.read(bFile);
						//System.out.println(uploads.get(i).length() + "," + bFile.length);
						bFiles.add(bFile);
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
				}
			}
		}
		return bFiles;
	}

	protected byte[] upload() {
		// 不是空文件
		if (null != uploadFile && uploadFile.length() > 0) {
			FileInputStream fis = null;
			byte[] bFile = null;
			try {
				if (getUploadFileFileName().indexOf(".asp") != -1 || getUploadFileFileName().indexOf(".jsp") != -1 || getUploadFileFileName().indexOf(".php") != -1 || getUploadFileFileName().indexOf(".sql") != -1) {
					throw new Exception("文件类型不符合要求！");
				}
				bFile = new byte[(int) uploadFile.length()];
				fis = new FileInputStream(uploadFile);
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

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public List<File> getUploads() {
		return uploads;
	}

	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	public File getProveFile() {
		return proveFile;
	}

	public void setProveFile(File proveFile) {
		this.proveFile = proveFile;
	}

	public File getOtherFile() {
		return otherFile;
	}

	public void setOtherFile(File otherFile) {
		this.otherFile = otherFile;
	}

	public List<String> getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	public List<String> getUploadsContentType() {
		return uploadsContentType;
	}

	public void setUploadsContentType(List<String> uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getProveFileFileName() {
		return proveFileFileName;
	}

	public void setProveFileFileName(String proveFileFileName) {
		this.proveFileFileName = proveFileFileName;
	}

	public String getOtherFileFileName() {
		return otherFileFileName;
	}

	public void setOtherFileFileName(String otherFileFileName) {
		this.otherFileFileName = otherFileFileName;
	}
}
