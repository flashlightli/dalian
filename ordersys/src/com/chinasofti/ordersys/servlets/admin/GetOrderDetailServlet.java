package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.entity.OrderDish;
import com.chinasofti.ordersys.entity.OrderInfo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetOrderDetailServlet
 */
@WebServlet("/GetOrderDetailServlet")
public class GetOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		JSONObject json = new JSONObject();
		OrderDao dao = new OrderDao();
		OrderInfo order = dao.searchById(orderId);
		List<OrderDish> dishes = dao.getDishes(orderId);
		json.put("order", order);
		json.put("dishes", dishes);
		json.put("dishesNum", dishes.size());
		response.getWriter().print(json.toString());
		System.out.println(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
