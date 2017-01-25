package com.guxiang.web.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.utils.PaymentUtil;


/*
 *    在线 支付的 的 代码实现 
 * 
 * 
 */
public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获得由 用户输入的 那部分的值 , 例如 ,支付的银行, 金额等等
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		String pd_FrpId1 = request.getParameter("pd_FrpId");
		
		String p0_Cmd ="Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = orderid;
		String p3_Amt =money;
		String p4_Cur ="CNY";
		String p5_Pid ="";
		String p6_Pcat ="";
		String p7_Pdesc ="";
		
		// 这 个是 url 地址, 是 支付公司通知 商户 支付 成功地 回调 的地址 
		//  需要有一个公网的 ip地址  
		
		String p8_Url ="http://192.168.0.102/callback";
		String p9_SAF ="";
		String pa_MP ="";
		String pd_FrpId =pd_FrpId1;   //具体的 支付 银行 
		String pr_NeedResponse ="1";
		
		//获得密钥 
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		
		//按照 算法 加密 
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);

		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
