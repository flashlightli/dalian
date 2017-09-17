package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ToOnlineKitchenServlet
 */
@WebServlet("/ToOnlineKitchenServlet")
public class ToOnlineKitchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToOnlineKitchenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//对request请求进行utf8-编码
		request.setCharacterEncoding("utf8");
		//将session中“user”赋给“USER_INFO”
		request.setAttribute("USER_INFO", request.getSession().getAttribute("user"));
		//请求转发到"pages/admin/onlinekitchens.jsp"页面
		request.getRequestDispatcher("pages/admin/onlinekitchens.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
