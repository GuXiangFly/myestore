package com.guxiang.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class WebUtils {

	public static Map doFileUpload(HttpServletRequest request) {
		try {
			// 拿工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 缓冲区大小
			factory.setSizeThreshold(1000 * 1000);
			// 存放位置
			factory.setRepository(new File(request.getSession()
					.getServletContext().getRealPath("/temp")));
			ServletFileUpload uploader = new ServletFileUpload(factory);
			// 解決文件名乱码
			uploader.setHeaderEncoding("UTF-8");
			uploader.setFileSizeMax(1024 * 1024 * 5);
			List<FileItem> list = uploader.parseRequest(request);
			Map<String, String> map = new HashMap<String, String>();

			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					// 普通输入项
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");
					map.put(fieldName, fieldValue);

				} else {
					// 文件上传项
					String name = fileItem.getName();
					int index = name.lastIndexOf("\\");

					if (index != -1) {
						name = name.substring(index + 1);
					}
					// 生成随机的文件名
					String uuidName = UploadUtils.generateRandonFileName(name);
					// 生成随机文件夹

					// /1/5
					String randomDir = UploadUtils.generateRandomDir(uuidName);

					// 不能放在webinfo目录下
					String imageurl = "/images" + randomDir;

					String realPath = request.getSession().getServletContext()
							.getRealPath("/images");
					// d:/a/b/mystore/images/1/5
					String newPath = realPath + randomDir;
					File file1 = new File(newPath);
					if (!file1.exists()) {
						file1.mkdirs();
					}
					File file = new File(newPath, uuidName);
					InputStream in = fileItem.getInputStream();
					OutputStream out = new FileOutputStream(file);
					int len = 1;
					byte[] buf = new byte[1024];
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
					// 删除临时文件
					fileItem.delete();
				
					map.put("imageurl", imageurl + "/" + uuidName);
				}
			}
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}


}
