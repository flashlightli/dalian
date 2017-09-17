package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.entity.OrderInfo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetRTPayOrderServlet
 */
@WebServlet("/GetRTPayOrderServlet")
public class GetRTPayOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRTPayOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf8");
		JSONObject json = new JSONObject();
		
		List<OrderInfo> orders = new OrderDao().getOrderByState(1);
		json.put("orders", orders);
		json.put("ordersNum", orders.size());
		System.out.println(json.toString());
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
