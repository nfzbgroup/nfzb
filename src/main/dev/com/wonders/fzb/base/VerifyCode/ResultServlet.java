package com.wonders.fzb.base.VerifyCode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* 产生结果servlet
*/
public class ResultServlet extends HttpServlet {

	private static final long serialVersionUID = -3426912671162080389L;

	/**  
	 * The doGet method of the servlet. <br>  
	 *  
	 * This method is called when a form has its tag value method equals to get.  
	 *   
	 * @param request the request send by the client to the server  
	 * @param response the response send by the server to the client  
	 * @throws ServletException if an error occurred  
	 * @throws IOException if an error occurred  
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**  
	 * The doPost method of the servlet. <br>  
	 *  
	 * This method is called when a form has its tag value method equals to post.  
	 *   
	 * @param request the request send by the client to the server  
	 * @param response the response send by the server to the client  
	 * @throws ServletException if an error occurred  
	 * @throws IOException if an error occurred  
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String validateC = (String) request.getSession().getAttribute("validateCode");
		String veryCode = request.getParameter("veryCode");
		System.out.println("validateC=" + validateC);
		System.out.println("veryCode=" + veryCode);
		PrintWriter out = response.getWriter();
		String rightCode = "";
		if (veryCode == null || "".equals(veryCode)) {
			rightCode = "验证码为空";
		} else {
			if (validateC.equals(veryCode)) {
				rightCode = "验证码正确";
			} else {
				rightCode = "验证码错误";
			}
		}
		request.getSession().setAttribute("QUESTION_ADD_RIGHT_CODE", rightCode);
		out.flush();
		out.close();
	}

}
