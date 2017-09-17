 package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.entity.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetOnlineKitchenServlet
 */
@WebServlet("/GetOnlineKitchenServlet")
public class GetOnlineKitchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOnlineKitchenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//对request请求和response响应进行utf-8编码
		request.setCharacterEncoding("UTF8");
		response.setCharacterEncoding("UTF8");
		//创建类UserDao的一个对象dao
		UserDao dao = new UserDao();
		//调用countOnline方法，并将结果赋给变量onlineNum
		int onlineNum = dao.countOnline();
		//调用getOnline方法，并将结果赋给List onlineKitchens 
		List<User> onlineKitchens = dao.getOnline(2);
		//将List onlineKitchens赋给变量onlineKitchenNum（在线kitchens数）
		int onlineKitchenNum = onlineKitchens.size();
		//创建一个对象json
		JSONObject json = new JSONObject();
		//将以下数据传到页面中
		json.put("onlineNum", onlineNum);
		json.put("onlineKitchenNum", onlineKitchenNum);
		json.put("onlineKitchens", onlineKitchens);
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
