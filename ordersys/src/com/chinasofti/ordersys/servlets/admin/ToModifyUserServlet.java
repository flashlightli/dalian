package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.entity.User;

/**
 * Servlet implementation class ToModifyUserServlet
 */
@WebServlet("/ToModifyUserServlet")
public class ToModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		request.setAttribute("USER_INFO", request.getSession().getAttribute("user"));
		User user = new UserDao().getUser(Integer.parseInt(request.getParameter("userId")));
		request.setAttribute("MODIFY_INFO", user);
		request.setAttribute("ORDER_SYS_NAME", "Order System");
		request.getRequestDispatcher("pages/admin/modifyuser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
