package com.chinasofti.ordersys.servlets.waiters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.ordersys.dao.DishDao;
import com.chinasofti.ordersys.entity.OrderDish;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
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
		List<OrderDish> dishes;
		if (session.getAttribute("dishes") != null) {
			dishes = (List<OrderDish>) session.getAttribute("dishes");
		} else {
			dishes = new ArrayList<>();
		}
		int dishId = Integer.parseInt(request.getParameter("dishes"));
		int num = Integer.parseInt(request.getParameter("num"));
		OrderDish dish = null;
		for (OrderDish d : dishes) {
			if (d.getDish().getId() ==  dishId) {
				dish = d;
			}
		}
		if (dish == null) {
			dish = new OrderDish();
			dish.getDish().setId(dishId);
			dish.setNum(num);
			dishes.add(dish);
		} else {
			dish.setNum(num);
		}
		session.setAttribute("dishes", dishes);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
