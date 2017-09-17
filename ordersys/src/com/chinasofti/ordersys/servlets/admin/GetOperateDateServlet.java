package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * Servlet implementation class GetOperateDateServlet
 */
@WebServlet("/GetOperateDateServlet")
public class GetOperateDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOperateDateServlet() {
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
		List<OrderInfo> orders = new ArrayList<>();
		try {
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			Date begin, end;
			if (!"".equals(beginDate)) {
				begin = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(beginDate);
			} else {
				begin = new Date(0L);
			}
			if (!"".equals(endDate)) {
				end = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);
			} else {
				end = new Date(System.currentTimeMillis());
			}
			orders = new OrderDao().searchByDate(begin, end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(orders);
		JSONObject json = new JSONObject();
		json.put("orders", orders);
		json.put("ordersNum", orders.size());
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
