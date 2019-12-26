package studentCurriculum;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdmTChange
 */
@WebServlet("/AdmTChange")
public class AdmTChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmTChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		TeacherBean teacherBean=new TeacherBean();
		teacherBean.setNum(request.getParameter("num"));
		teacherBean.setName(request.getParameter("name"));
		teacherBean.setSex(request.getParameter("sex"));
		teacherBean.setAge(Integer.parseInt(request.getParameter("age")));
		teacherBean.setRank(request.getParameter("rank"));
		teacherBean.setPhone(request.getParameter("phone"));
		AdmDao admDao=new AdmDao();
		boolean flag=admDao.changTeacher(teacherBean);
		if(!flag) {
			request.setAttribute("flag", "编辑教师信息错误！");
		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher("AdmTeacher");
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
