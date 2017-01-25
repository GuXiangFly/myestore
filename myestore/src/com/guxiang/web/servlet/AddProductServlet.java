package com.guxiang.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.guxiang.domain.Products;
import com.guxiang.service.ProductService;
import com.guxiang.utils.WebUtils;

public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
				//不是文件上传
			request.setAttribute("message", "对不起，您的表单不是文件上传的表单，请确认后操作");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		Map map= WebUtils.doFileUpload(request);
		Products prodect = new Products();
		try {
			BeanUtils.populate(prodect, map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println(prodect);
		//调用业务层去封装数据
		ProductService  ps = new ProductService();
		ps.addProduct(prodect);
		
		//提示
		request.getSession().setAttribute("message", "添加成功了");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
