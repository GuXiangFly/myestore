package com.guxiang.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
/*
 *  通用的解决全站的 请求参数数据乱码的过滤器 
 *  	
 *  	请求过来时, 浏览器端 过来的请求方式 只有 get 和post 
 *  
 *  	
 *  	思考 : 获得  请求参数 :
 *  
 *  	request.getParameter("name");  --- 单个的 
 *  	request.getParameterValues("hobbies");  ----返回是 数组 
 *  	request.getParameterMap();   --- Map<String, String[]> getParameterMap
 *  
 *  	要获得参数, 就得 使用 如上的三个方法 , 
 *  	
 *  //	分析的结果是, 需要去 重写 如上三个方法, 但是 由于 getParameterMap 中包含的有 所有的请求的参数 信息, 所以 
 *  	可以 将 其他的两个 方法 ,依赖于这一个 方法, 我们的中心 放在这个  getParameterMap 方法中就可以了. 将来调用了
 *  	其他的那 两个方法时, 只需要去map 中取出 对应的参数值就可以 了 . 
 *  
 * 
 */
public class GenericEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setContentType("text/html;charset=utf-8");
		
		// 要解决乱码 --- 能不能  自定义 一个包装类, 去包装这个 request 对象, 
		
		MyHttpServletRequestWrapper myrequest = new MyHttpServletRequestWrapper(request);
		
		
		chain.doFilter(myrequest, response);  //放行 , 目标资源会得到 执行 
		
	}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	

	@Override
	public void destroy() {

	}

}

class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{

	private HttpServletRequest request;

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		
		Map<String,String[]> map = getParameterMap();
		//获得对应  values 数组 
		String[] values = map.get(name);
		
		if(values!=null){
			
			//返回数组的第一个元素 
			return values[0];
		}
		return null;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String,String[]> map = getParameterMap();
		//获得对应  values 数组 
		String[] values = map.get(name);
		return values;
	}
	
	//默认的是false, 表示没有 被 解码 过 
	private boolean hasBeenEncoded= false;
	
	@Override
	public Map getParameterMap() {
		
		//只有 get 和post 
		
		//获得请求 方式
		String method = request.getMethod();
		
		if("post".equalsIgnoreCase(method)){
			
			//如果 进来,表示 是post请求方式
			
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
			//说明是get 
			
			//拿到 现有的乱码的 map 
			Map<String,String[]> map = request.getParameterMap();
			
			//遍历 map 
			Set<String> keys = map.keySet();
			
			// 乱码的本质 原因是, 这里 的 for 循环里的逻辑 被重复的调用了, 正常的情况下, 应该是只 调用一次, 就会解决乱码
			// 如何确保这里 的for循环里的 代码 只被 调用一次  
			
			
			//这里 定义 布尔值, 实现 代码 只被 调用一次, 是实际开发过程中,非常 常用的.  (技巧性的代码)
			if(!hasBeenEncoded){
				hasBeenEncoded = true;
			
				for (String key : keys) {
					//乱码的 
					String[] values = map.get(key);
					for (int i = 0; i < values.length; i++) {
						
						try {
							System.out.println("之前: " + values[i]);
							values[i] = new String(values[i].getBytes("ISO8859-1"),"UTF-8");
							System.out.println("之后: " + values[i]);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return map;
		}
		return super.getParameterMap();
	}
	
}

