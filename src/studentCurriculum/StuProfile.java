package studentCurriculum;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.websocket.Session;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/StuProfile")
public class StuProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StuProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StuDao stuDao = new StuDao();
		StudentBean studentBean = new StudentBean();
		String num = "";
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("num"))
					num = cookie.getValue();
			}
		}
		String num2=(String) request.getAttribute("num");
		if(!num.equals(num2)&&num2!=null) {
			num=num2;
		}
		studentBean = stuDao.getStudent(num);
		request.setAttribute("stu", studentBean);
		
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/stuindex.jsp");
		rDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

