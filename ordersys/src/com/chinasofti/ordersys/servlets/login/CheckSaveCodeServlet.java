package com.chinasofti.ordersys.servlets.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckSaveCodeServlet")
public class CheckSaveCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSaveCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strCode=request.getParameter("code");
		// 获取会话中保存的验证码
		String sessionCode = request.getSession().getAttribute("checkCode").toString();
		// 定义一个json对象
		String status;
		/*JSONObject object = new JSONObject();*/
		if(strCode.equalsIgnoreCase(sessionCode)){
			status = "OK";
			//object.put("strMessage", "1");
		}else{
			status = "FAIL";
			//object.put("strMessage", "0");
		}
		PrintWriter out = response.getWriter();
		out.write(status);
		//out.write(object.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
