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
 * Servlet implementation class GetOnlineWaitersServlet
 */
@WebServlet("/GetOnlineWaitersServlet")
public class GetOnlineWaitersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOnlineWaitersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��request�����response��Ӧ����utf-8����
		request.setCharacterEncoding("UTF8");
		response.setCharacterEncoding("UTF8");
		//������UserDao��һ������dao
		UserDao dao = new UserDao();
		//����countOnline���������������������onlineNum
		int onlineNum = dao.countOnline();
		//����getOnline�����������������List onlineWaiters 
		List<User> onlineWaiters = dao.getOnline(3);
		//��List onlineWaiters��������onlineWaiterNum������waiters����
		int onlineWaiterNum = onlineWaiters.size();
		//����һ������json
		JSONObject json = new JSONObject();
		//���������ݴ���ҳ����
		json.put("onlineNum", onlineNum);
		json.put("onlineWaiterNum", onlineWaiterNum);
		json.put("onlineWaiters", onlineWaiters);
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
