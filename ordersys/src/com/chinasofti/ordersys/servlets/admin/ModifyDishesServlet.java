package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.DishDao;

/**
 * Servlet implementation class ModifyDishesServlet
 */
@WebServlet("/ModifyDishesServlet")
public class ModifyDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDishesServlet() {
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
		String dishesName=request.getParameter("dishesName");
		String dishesDiscript=request.getParameter("dishesDiscript");
		String dishesTxt=request.getParameter("dishesTxt");
		float dishesPrice=Float.parseFloat(request.getParameter("dishesPrice"));
		String recommend=request.getParameter("recommend");
		int dishesId=Integer.parseInt(request.getParameter("dishesId"));
		
		
		if(new DishDao().updatedishes(dishesName, dishesDiscript, dishesTxt, dishesPrice,recommend,dishesId)){
			request.getRequestDispatcher("todishesadmin.order").forward(request, response);
		}else{
			response.sendRedirect("tomodifydishes.order?dishesId=" + dishesId);;
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
