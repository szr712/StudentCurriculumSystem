package studentCurriculum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdmCChange
 */
@WebServlet("/AdmCChange")
public class AdmCChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmCChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		CourseBean courseBean =new CourseBean();
		courseBean.setCoursenum(request.getParameter("num"));
		courseBean.setCoursename(request.getParameter("name"));
		courseBean.setTime(Integer.parseInt(request.getParameter("time")));
		courseBean.setTestway(request.getParameter("testway"));
		courseBean.setCredit(Float.parseFloat(request.getParameter("credit")));
		AdmDao admDao=new AdmDao();
		boolean flag=admDao.changeCourse(courseBean);
		if(!flag) {
			request.setAttribute("flag", "编辑课程信息错误！");
		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher("AdmCourse");
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
