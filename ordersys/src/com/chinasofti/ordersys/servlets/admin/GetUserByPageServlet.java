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
 * Servlet implementation class GetUserByPageServlet
 */
@WebServlet("/GetUserByPageServlet")
public class GetUserByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��request�����response��Ӧ����utf-8����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//��ajax��������
		response.setContentType("text/json; charset=UTF8");
		//������UserDao��һ������dao
		UserDao dao = new UserDao();
		//��ҳ���ȡ��page������1�󸳸�page
		int page = Integer.parseInt(request.getParameter("page")) - 1;
		//����dao����count�����������������������count
		int count = dao.count();
		//ÿҳ��ʾ5����¼����¼����count����5��Ϊ���ҳ����
		int maxPage = count / 5;
		//���maxPageΪ0��maxPage��1
		if (count % 5 != 0) {
			maxPage++;
		}
		// ��ֹҳ��Խ��
		if (page < 0) {
			page = 0;
		} else if (page >= maxPage) {
			page = maxPage - 1;
		}
		//����dao����getUserByPage��������,�����������user
		List<User> users = dao.getUserByPage(page, 5);
		//��������json
		JSONObject json = new JSONObject();
		//���������ݴ���ҳ����
		json.put("page", page + 1);
		json.put("count", count);
		json.put("maxPage", maxPage);
		json.put("users", users);
		json.put("usersCount", users.size());
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
