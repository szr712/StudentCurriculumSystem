package studentCurriculum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeaIn
 */
@WebServlet("/TeaIn")
public class TeaIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeaIn() {
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
		TeaDao teaDao = new TeaDao();
		String coursenum = request.getParameter("coursenum");
		String counum = (String) request.getParameter("num");
		
		String year = (String) request.getParameter("year");
		request.setAttribute("year", year);
		request.setAttribute("num", counum);
		String num1 = counum;

		Cookie cookie = null;
		String num = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("num"))
					num = cookie.getValue();
			}
		}
		ArrayList<StudentBean> sList = new ArrayList<>();
		if (coursenum == null) {
			if (counum != null) {
				counum = counum.substring(counum.indexOf("（") + 1, counum.length() - 1);

				sList = new ArrayList<>();
				sList = teaDao.getSingle(num, counum, year, 1);
				if (sList.isEmpty()) {
					request.setAttribute("flag", num1 + "查无数据");
				}

			}
		} else {
			float grade = Float.parseFloat(request.getParameter("grade"));
			String stunum = request.getParameter("stunum");
			boolean flag = teaDao.changeGrade(grade, coursenum, stunum);
			if (flag) {
				sList = new ArrayList<>();
				coursenum = coursenum.substring(0, 5);
				
				sList = teaDao.getSingle(num, coursenum, year, 1);
			} else {
				request.setAttribute("flag", "录入出现错误！");
			}

		}

		ArrayList<String> aList = teaDao.getACourse(num);
		request.setAttribute("alist", aList);
		request.setAttribute("slist", sList);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/teain.jsp");
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
