package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.entity.OrderDish;
import com.chinasofti.ordersys.entity.OrderInfo;
import com.chinasofti.ordersys.entity.User;

/**
 * Servlet implementation class CommitCartServlet
 */
@WebServlet("/CommitCartServlet")
public class CommitCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<OrderDish> dishes = (List<OrderDish>) session.getAttribute("dishes");
		OrderInfo order = new OrderInfo();
		order.setOrderState(0);
		order.setOrderBeginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		order.setTableId(Integer.parseInt(String.valueOf(session.getAttribute("tableId"))));
		order.setWaiter((User) session.getAttribute("user"));
		System.out.println(order);
		System.out.println(dishes);
		new OrderDao().addOrder(order, dishes);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
