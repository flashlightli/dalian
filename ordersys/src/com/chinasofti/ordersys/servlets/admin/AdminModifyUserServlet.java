package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.tools.MD5;

/**
 * Servlet implementation class AdminModifyUserServlet
 */
@WebServlet("/AdminModifyUserServlet")
public class AdminModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userPass = MD5.md5(request.getParameter("userPass"));
		int role = Integer.parseInt(request.getParameter("roleId"));
		if (new UserDao().UpdateuserPass(userId, userPass, role)) {
			response.sendRedirect("touseradmin.order");
		} else {
			response.sendRedirect("tomodifyuser.order?userId=" + userId);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
