package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.DishDao;
import com.chinasofti.ordersys.entity.Dish;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetDishesInfoByPageServlet
 */
@WebServlet("/GetDishesInfoByPageServlet")
public class GetDishesInfoByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDishesInfoByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//对request请求和response响应进行utf-8编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//用ajax传输数据
		response.setContentType("text/json; charset=UTF8");
		//创建类DishDao的一个对象dao
		DishDao dao = new DishDao();
		//从页面获取“page”，减1后赋给page
		int page = Integer.parseInt(request.getParameter("page")) - 1;
		//对象dao调用count（）方法，并将结果赋变量count
		int count = dao.count();
		//每页显示5条记录，记录总数count除以5即为最大页面数
		int maxPage = count / 5;
		//如果maxPage为0，maxPage加1
		if (count % 5 != 0) {
			maxPage++;
		}
		// 防止页码越界
		if (page < 0) {
			page = 0;
		} else if (page >= maxPage) {
			page = maxPage - 1;
		}
		//对象dao调用getDishByPage（）方法,并将结果赋给dishes
		List<Dish> dishes = dao.getDishByPage(page, 5);
		//创建一个对象json
		JSONObject json = new JSONObject();
		//将以下数据传到页面中
		json.put("page", page + 1);
		json.put("count", count);
		json.put("maxPage", maxPage);
		json.put("dishes", dishes);
		json.put("dishesCount", dishes.size());
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
