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

import jdk.nashorn.internal.ir.WithNode;

/**
 * Servlet implementation class TeaCourse
 */
@WebServlet("/TeaCourse")
public class TeaCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String year=(String) request.getParameter("year");
		String colnum=(String) request.getParameter("colnum");
		String num="";
		Cookie cookie = null;
		TeaDao teaDao=new TeaDao();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("num"))
					num = cookie.getValue();
			}
		}
		if(colnum!=null) {
			year=colnum.substring(10,14);
			teaDao.Withdraw(colnum);
		}
		if(year==null) {
			ArrayList<CourseBean> cList=new ArrayList<>();
			request.setAttribute("clist", cList);
			
		}
		else {
			ArrayList<CourseBean> cList=teaDao.getCourse(num, year);
			if(cList.isEmpty()) {
				request.setAttribute("flag", year+"年查询无结果");
			}
			request.setAttribute("clist", cList);
		}
		request.setAttribute("year", year);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/teacourse.jsp");
		rDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
