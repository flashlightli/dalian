package com.chinasofti.ordersys.servlets.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.entity.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PreviewImageUploadServlet
 */
@WebServlet("/PreviewImageUploadServlet")
public class PreviewImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewImageUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		//json����
		JSONObject json = new JSONObject();
		//������Ϣ����Ϊ���ַ����������󷵻ص��ǿ��ַ�����˵���ϴ��ɹ���
		json.put("message","");
		json.put("url","");
		//���ս��Ϊtrue˵���ϴ����
		boolean glStrError = true;
		// �ļ�����Ŀ¼·��
		String savePath = request.getServletContext().getRealPath("/") + "img/faces/";
		// �ļ�����Ŀ¼URL
		String saveUrl = request.getContextPath() + "/img/faces/";
		System.out.println(savePath);
		System.out.println(saveUrl);

		// ���������ϴ����ļ���չ��
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		// ����ļ���С
		long maxSize = 4000000;
		File uploadDir = new File(savePath);
		
		// ���Ŀ¼��û�оʹ���Ŀ¼
		if (!uploadDir.exists() && !uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		//String dirName="image";
		if (!ServletFileUpload.isMultipartContent(request)) {//�ж��Ƿ�ѡ�����ϴ���ͷ��
			json.put("message", "��ѡ��ͷ��");
			
		}  else if (!uploadDir.canWrite()) {// ���Ŀ¼дȨ��
			json.put("message", "�ϴ�Ŀ¼û��дȨ�ޡ�");
		} 
		else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//���ñ��������������
			upload.setHeaderEncoding("UTF-8");
			try {
				List<?> items = upload.parseRequest(request);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();
					if (!item.isFormField()) {
						// �����չ��
						String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						// ����ļ���С
						if (item.getSize() > maxSize) {
							json.put("message", "�ϴ��ļ���С�������ơ�");
						} else if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
							json.put("message", "�ϴ��ļ���չ���ǲ��������չ����\nֻ����" + extMap.get("image") + "��ʽ��");
						} else {
							//���ϴ��ļ�������������ֹ�ļ����ظ�����ͬʱҲ�Ƕ�ʱ�����͵Ĵ���
							/**
							 * //�Ի�ȡ��ʱ����и�ʽ��
							 *SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							 *String newFileName11 = df11.format(new Date());
							 *System.out.println(newFileName11);
							 */
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
							String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
									+ fileExt;
							try {
								//�ϴ��ļ�
								File uploadedFile = new File(savePath, newFileName);
								item.write(uploadedFile);
							} catch (Exception e) {
								glStrError = false;
								json.put("message", "�ϴ��ļ�ʧ�ܡ�");
							}
							if (glStrError) {
								/*��url�����û���Ӧ�����ݿ���� ���д���
								 * ���û��ٴε�¼��ʱ���ж��Ƿ���ͷ��
								 * �����ͷ��ȡ����Ӧ���ͻ���
								 * �ͻ��˰�URL���뵽img��src������ʾͷ��
								 * */
								new UserDao().updateFaceImg(((User) request.getSession().getAttribute("user")).getUserAccount(), newFileName);
								String url= saveUrl + newFileName;
								json.put("url",url);
							}
						}
					}
				}
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.getStackTrace();
			}
		}
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
