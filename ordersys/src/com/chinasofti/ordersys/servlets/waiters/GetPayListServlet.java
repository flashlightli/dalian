package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.entity.OrderInfo;
import com.chinasofti.ordersys.entity.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetPayListServlet
 */
@WebServlet("/GetPayListServlet")
public class GetPayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPayListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrderDao dao = new OrderDao();
		int page = Integer.parseInt(request.getParameter("page")) - 1;
		int count = dao.count();
		int maxPage = count / 5;
		if (count % 5 != 0) {
			maxPage++;
		}
		if (page < 0) {
			page = 0;
		} else if (page >= maxPage) {
			page = maxPage - 1;
		}
		List<OrderInfo> orders = dao.getOrderByPage(page, 5);
		JSONObject json = new JSONObject();
		json.put("page", page + 1);
		json.put("count", count);
		json.put("maxPage", maxPage);
		json.put("orders", orders);
		json.put("ordersCount", orders.size());
		response.getWriter().print(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
