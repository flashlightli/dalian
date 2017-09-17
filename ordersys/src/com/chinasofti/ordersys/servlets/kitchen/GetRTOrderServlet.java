package com.chinasofti.ordersys.servlets.kitchen;

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

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetRTOrderServlet
 */
@WebServlet("/GetRTOrderServlet")
public class GetRTOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRTOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		JSONObject json= new JSONObject();
		List<OrderDish> orderDishs = new OrderDao().getrtdishes();
		json.put("orderDishes", orderDishs);
		json.put("orderDishesNum", orderDishs.size());
		System.out.println(json.toString());
		response.getWriter().println(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
