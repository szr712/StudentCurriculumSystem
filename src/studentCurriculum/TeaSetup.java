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
 * Servlet implementation class TeaSetup
 */
@WebServlet("/TeaSetup")
public class TeaSetup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaSetup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TeaDao teaDao = new TeaDao();
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
		String colnum=(String) request.getParameter("colnum");
		//System.out.println(colnum);
		if(colnum!=null) {
			teaDao.SetCourse(num, colnum);
		}
		ArrayList<CourseBean> cList = new ArrayList<>();
		CourseBean courseBean = new CourseBean();
		cList = teaDao.getNoSet(num);
		request.setAttribute("clist", cList);
		if(cList.isEmpty()) {
			request.setAttribute("flag", "现无可开课程");
		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/teasetup.jsp");
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
