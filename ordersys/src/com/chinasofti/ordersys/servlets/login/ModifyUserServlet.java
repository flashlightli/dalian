package com.chinasofti.ordersys.servlets.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.entity.User;
import com.chinasofti.tools.MD5;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getUserId();
		String userPass = MD5.md5(request.getParameter("userPass"));
		int role = user.getRoleId();

		boolean b = new UserDao().UpdateuserPass(userId, userPass, role);
		if (b) {
			if (role == 1) {
				request.getRequestDispatcher("pages/admin/main.jsp").forward(request, response);
			} else if (role == 2) {
				request.getRequestDispatcher("pages/kitchen/kitchenmain.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("pages/waiters/takeorder.jsp").forward(request, response);
			}

		} else {
			request.getRequestDispatcher("pages/users/modifyuser.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
