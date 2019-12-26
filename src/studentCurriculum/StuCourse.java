package studentCurriculum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StuCourse
 */
@WebServlet("/StuCourse")
public class StuCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StuCourse() {
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
		StuDao stuDao = new StuDao();
		String colnum=request.getParameter("colnum");
		if(colnum!=null) {
			stuDao.Withdrawal(num, colnum);
		}
		ArrayList<CourseBean> cList = new ArrayList<>();
		CourseBean courseBean = new CourseBean();
		cList = stuDao.getCourse(num);
		cList.sort(new Comparator<CourseBean>() {

			@Override
			public int compare(CourseBean o1, CourseBean o2) {
				// TODO Auto-generated method stub
				if(o1.getYear().compareTo(o2.getYear())!=0) {
					return o1.getYear().compareTo(o2.getYear());
				}else {
					return o1.getTerm().compareTo(o2.getTerm());
				}
			}
			
		});
		request.setAttribute("clist", cList);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/stucourse.jsp");
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
