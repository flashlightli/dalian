package com.chinasofti.ordersys.servlets.common;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.tools.SaveCode;

/**
 * Servlet implementation class SaveCodeServlet
 */
@WebServlet("/SaveCodeServlet")
public class SaveCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SaveCode saveCode=new SaveCode("abcdefghijklmnopqrstuvwxyz123456789".toUpperCase().toCharArray(),
				100, 25, 6);
		// 图片不需要缓存的响应头
		response.setHeader("Pragma", "No-cache");
		// 图片不需要缓存的响应头
		response.setHeader("Cache-Control", "no-cache");
		// 图片不需要缓存的响应头
		response.setDateHeader("Expires", 0);
		// 设置响应MIME类型为JPEG图片
		response.setContentType("image/jpeg");
		// 创建验证码图片
		saveCode.createSaveCodeImage();
		// 获取验证码图片
		BufferedImage img = saveCode.getImage();
		// 获取验证码字符串
		String codeString = saveCode.getCodeString();
		// 获取会话对象
		HttpSession session = request.getSession();
		// 将验证码字符串存入会话
		session.setAttribute("checkCode", codeString);
		try {
			// 将缓存图片编码为物理图片数据并从响应输出流中输出到客户端
			ImageIO.write(img, "JPEG", response.getOutputStream());
			// 捕获异常
		} catch (Exception e) {

			// TODO: handle exception
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
