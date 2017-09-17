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
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String account = request.getParameter("userAccount");
		String password = MD5.md5(request.getParameter("userPass"));
		UserDao dao = new UserDao();
		User user = dao.getUser(account);
		String msg;
		if (user != null) {
			if (password.equals(user.getUserPass())) {
				msg = "登录成功";
				user.setUserPass("");
				request.setAttribute("USER_INFO", user);
				request.getSession().setAttribute("user", user);
				dao.setOnline(user.getUserId(), 1);
				int role = user.getRoleId();
				if (role == 1) {
					request.getRequestDispatcher("pages/admin/main.jsp").forward(request, response);
				} else if (role == 2) {
					request.getRequestDispatcher("pages/kitchen/kitchenmain.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("pages/waiters/takeorder.jsp").forward(request, response);
				}
			} else {
				msg = "密码错误";
				response.sendRedirect("pages/login.jsp");
			}
		} else {
			msg = "用户名不存在的";
			response.sendRedirect("pages/login.jsp");
		}
		response.getWriter().println("<script>alert('" + msg + "')</script>");
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
