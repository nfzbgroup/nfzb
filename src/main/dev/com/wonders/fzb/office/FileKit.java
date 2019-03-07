package com.wonders.fzb.office;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * 文件操作类
 * @author ZSW 2017-04-12
 */
public class FileKit {
	
	/**
	 * 读取文件byte流（传统IO） 本函数加载效率高，但适合加载小型文件（50M内）
	 * 如抛出OutOfMemery请尝试根据加载文件大小提升JVM运行内存参数或使用本工具类中其他函数（详情参见具体函数注释）
	 * 
	 * @param filePath            文件路径
	 */
	@SuppressWarnings("resource")
	public byte[] fastLoadFileContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();

		// 设置文件最大大小
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("文件太大");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("没有完全加载文件： " + file.getName() + "，请重新尝试。");
		}
		fi.close();
		return buffer;
	}

	/**
	 * 加载文件（传统IO） 使用内存缓冲区加载文件，能够读取较大文件（建议不要超过1GB）
	 * 如抛出OutOfMemery请尝试根据加载文件大小提升JVM运行内存参数或使用本工具类中其他函数（详情参见具体函数注释）
	 */
	public static byte[] loadFileContent(String fileName) throws IOException {

		File f = new File(fileName);
		if (!f.exists()) {
			throw new FileNotFoundException(fileName);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 加载文件（NIO）
	 */
	public static byte[] loadFile(String fileName) throws IOException {

		File f = new File(fileName);
		if (!f.exists()) {
			throw new FileNotFoundException(fileName);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 加载大文件（NIO文件内存映射、文件通道）
	 */
	@SuppressWarnings("resource")
	public static byte[] loadBigFile(String fileName) throws IOException {

		FileChannel fc = null;
		try {
			fc = new RandomAccessFile(fileName, "r").getChannel();
			// MappedByteBuffer 可以在处理大文件时，提升性能
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 生成文件
	 * @param filePath
	 * @param data
	 */
	public static void generateFile(String filePath,byte[] data){
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(filePath);
			fos.write(data);
			fos.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * byte数组加密成Base64
	 */
	public static String bytes2base64(byte[] data) {
		return Base64.encode(data);
	}

	/**
	 * base64还原成byte数组
	 */
	public static byte[] base642bytes(String base64code) {
		byte[] result = null;
		try {
			result = Base64.decode(base64code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
