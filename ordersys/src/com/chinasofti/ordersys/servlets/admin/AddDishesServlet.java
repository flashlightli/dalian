package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.DishDao;

/**
 * Servlet implementation class AddDishesServlet
 */
@WebServlet("/AddDishesServlet")
public class AddDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDishesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String dishesName = request.getParameter("dishesName");
		String dishesDiscript = request.getParameter("dishesDiscript");
		String dishesTxt = request.getParameter("dishesTxt");
		int recommend ;
		if(request.getParameter("recommend") != null) {
			recommend=1;
		}else {
			recommend=0;
		}
		int dishesPrice = Integer.parseInt(request.getParameter("dishesPrice"));
		boolean b = new DishDao().adddishes(dishesName, dishesDiscript, dishesTxt,recommend,dishesPrice);
		if(b){
			request.getRequestDispatcher("/pages/admin/dishesadmin.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/pages/admin/adddishes.jsp").forward(request, response);
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
