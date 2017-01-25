package com.guxiang.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*  
 * 		生成验证码 图片  : 回去选作 .
 * 
 */
public class CheckImageServlet extends HttpServlet {
	
	//变大小写 
	private final int WIDTH =120;  // ctrl+shift+x / y 
	private final int HEIGHT =30;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 首先需要在内存中构建一张图片出来
		BufferedImage bf = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = (Graphics2D) bf.getGraphics();
		
		//设置背景颜色 
		Color color = new Color(203, 222, 225);
		graphics.setColor(color);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		String base ="ABCDEFGHIJKLMN";
		Random random = new Random();
		
		// 状态机: 如果你设置过之前的颜色, 角度.弧度, 大小等等这些信息, 那么后续的情况下,你如果没有 再去  调用对应api 去更改这些只, 那么仍然将保持之前你设置过的状态
		StringBuffer sb = new StringBuffer();
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("楷体",Font.BOLD,18));
		int m = 13;
		// 画4 个字符 
		for (int i = 0; i < 4; i++) {
			
			int index = random.nextInt(base.length());
			char charAt = base.charAt(index);
			sb.append(charAt);
			//  -30 --- 30      15
			int jiaodu = random.nextInt(60)-30;
			
//			角度变 弧度:　 初中的时候学的 
			double theta = jiaodu*Math.PI/180;
			
			//这里theta 要的是 弧度, 
			graphics.rotate(theta,  m, 15);
			graphics.drawString(charAt+"", m, 19);
			graphics.rotate(-theta,  m, 15);
			m+=20;
		}
		request.getSession().setAttribute("checkcode_session", sb.toString());
		
		// 画 4 条干扰线 
		graphics.setColor(Color.BLUE);
		for (int i = 0; i < 4; i++) {
			//生成随机的  坐标点 
			int x1 = random.nextInt(WIDTH);
			int x2 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			int y2 = random.nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);
		}
		
		// 释放资源
		graphics.dispose();
		
		
		ImageIO.write(bf, "png", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
