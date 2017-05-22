/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */

package com.resoft.prophet.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件相关处理工具类。
 * 
 * @author 伍军军
 */
public class FileUtils {

	/**
	 * 上传文件。<br/>
	 * 
	 * @param file 文件对象
	 * @param path 上传路径
	 * @param fileName 文件名称
	 * 
	 * @return boolean
	 * 
	 * @throws Exception
	 */
	public static boolean upload(File file, String filePath, String fileName) throws Exception {
		boolean uploadiIsucceed = false;
		String ret = "";
		FileOutputStream outputStream = null;
		FileInputStream fileIn = null;
		try {
			if (file != null) {
				createDir(filePath);
				ret = filePath + "/" + fileName;
				outputStream = new FileOutputStream(ret);
				fileIn = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fileIn.read(buffer)) > 0) {
					outputStream.write(buffer, 0, len);
				}
				uploadiIsucceed = true;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (fileIn != null) {
				fileIn.close();
			}
		}
		return uploadiIsucceed;
	}

	/**
	 * 创建文件目录 。<br/>
	 * 
	 * @param file 文件对象
	 * @param path 上传路径
	 * @param fileName 文件名称
	 * 
	 * @return boolean
	 * 
	 * @throws Exception
	 */
	private static void createDir(String pathName) {
		File dir;
		dir = new File(pathName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 根据文件后缀得到相应文件类型。<br/>
	 * 
	 * @param fileName 文件名称
	 * 
	 * @return String
	 * 
	 */
	public static String getContentType(String fileName) {
		String fileNameTmp = fileName.toLowerCase();
		String ret = "";
		if (fileNameTmp.endsWith("txt")) {
			ret = "text/plain";
		}
		if (fileNameTmp.endsWith("gif")) {
			ret = "image/gif";
		}
		if (fileNameTmp.endsWith("jpg")) {
			ret = "image/jpeg";
		}
		if (fileNameTmp.endsWith("jpeg")) {
			ret = "image/jpeg";
		}
		if (fileNameTmp.endsWith("jpe")) {
			ret = "image/jpeg";
		}
		if (fileNameTmp.endsWith("zip")) {
			ret = "application/zip";
		}
		if (fileNameTmp.endsWith("rar")) {
			ret = "application/rar";
		}
		if (fileNameTmp.endsWith("doc")) {
			ret = "application/msword";
		}
		if (fileNameTmp.endsWith("ppt")) {
			ret = "application/vnd.ms-powerpoint";
		}
		if (fileNameTmp.endsWith("xls")) {
			ret = "application/vnd.ms-excel";
		}
		if (fileNameTmp.endsWith("html")) {
			ret = "text/html";
		}
		if (fileNameTmp.endsWith("htm")) {
			ret = "text/html";
		}
		if (fileNameTmp.endsWith("tif")) {
			ret = "image/tiff";
		}
		if (fileNameTmp.endsWith("tiff")) {
			ret = "image/tiff";
		}
		if (fileNameTmp.endsWith("pdf")) {
			ret = "application/pdf";
		}
		return ret;
	}

	/**
	 * 下载文件，默认字符集编码为GBK。<br/>
	 * 
	 * @param response HttpServletResponse
	 * @param fileName 文件名称
	 * @param path 文件路径
	 * 
	 */
	public static void downFile(HttpServletResponse response, String fileName, String path) {
		downFile(response, fileName, path, "GBK");
	}

	/**
	 * 下载文件。<br/>
	 * 
	 * @param response HttpServletResponse
	 * @param fileName 文件名称
	 * @param path 文件路径
	 * @param charset 字符集编码
	 * 
	 */
	public static void downFile(HttpServletResponse response, String fileName, String path, String charset) {
		OutputStream fos = null;
		InputStream fis = null;

		try {
			response.setContentType(getContentType(fileName));
			response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(charset), "iso8859-1"));
			fis = new FileInputStream(path);
			fos = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			response.reset();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载文件，默认字符集编码为GBK。<br/>
	 * 
	 * @param response HttpServletResponse
	 * @param fileName 文件名称
	 * @param is InputStream
	 * 
	 */
	public static void downFile(HttpServletResponse response, String fileName, InputStream is) {
		downFile(response, fileName, is, "GBK");
	}

	/**
	 * 下载文件。<br/>
	 * 
	 * @param response HttpServletResponse
	 * @param fileName 文件名称
	 * @param is InputStream
	 * @param charset 字符集编码
	 * 
	 */
	public static void downFile(HttpServletResponse response, String fileName, InputStream is, String charset) {
		OutputStream fos = null;
		InputStream fis = is;

		try {
			response.setContentType(getContentType(fileName));
			response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(charset), "iso8859-1"));
			fos = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			response.reset();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件下载，默认字符集编码为GBK。<br/>
	 * 
	 * @param response
	 * @param serverFile 用于下载的路径及文件名
	 * @param fileName 下载显示的文件名
	 * 
	 * @throws Exception
	 */
	public static void downLoadFile(HttpServletResponse response, String serverFile, String fileName) throws IOException {
		downLoadFile(response, serverFile, fileName, "GBK");
	}

	/**
	 * 文件下载。<br/>
	 * 
	 * @param response
	 * @param serverFile 用于下载的路径及文件名
	 * @param fileName 下载显示的文件名
	 * @param charset 字符集编码
	 * 
	 * @throws Exception
	 */
	public static void downLoadFile(HttpServletResponse response, String serverFile, String fileName, String charset) throws IOException {
		File f = null;
		InputStream in = null;
		OutputStream sos = null;

		// 设置文件头
		response.reset();
		response.setContentType("application/x-msdownload; charset=" + charset);
		response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(charset), "ISO-8859-1"));

		// 读取文件信息, 得到输出流, 向浏览器输出
		try {
			f = new File(serverFile);

			response.setContentLength((int) f.length());
			in = new BufferedInputStream(new FileInputStream(f));

			sos = new BufferedOutputStream(response.getOutputStream());

			byte[] b = new byte[10240];
			int len = 0;
			while ((len = in.read(b)) > 0) {
				sos.write(b, 0, len);
			}
		} catch (IOException e) {
			throw e;

		} finally {
			try {
				// 关闭文件输入流
				if (in != null) {
					in.close();
				}
				// 写入浏览器，下载文件
				if (sos != null) {
					sos.close();
				}
				if (f != null) {
					f.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
