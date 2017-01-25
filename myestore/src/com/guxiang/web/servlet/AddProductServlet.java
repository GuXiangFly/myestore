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
				//�����ļ��ϴ�
			request.setAttribute("message", "�Բ������ı������ļ��ϴ��ı�����ȷ�Ϻ����");
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
		//����ҵ���ȥ��װ����
		ProductService  ps = new ProductService();
		ps.addProduct(prodect);
		
		//��ʾ
		request.getSession().setAttribute("message", "��ӳɹ���");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
