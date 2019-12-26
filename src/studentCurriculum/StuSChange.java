package studentCurriculum;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StuSChange
 */
@WebServlet("/StuSChange")
public class StuSChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuSChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		StudentBean studentBean=new StudentBean();
		studentBean.setNum(request.getParameter("num"));
		studentBean.setName(request.getParameter("name"));
		studentBean.setSex(request.getParameter("sex"));
		studentBean.setAge(Integer.parseInt(request.getParameter("age")));
		studentBean.setAge(Integer.parseInt(request.getParameter("age")));
		studentBean.setPlace(request.getParameter("place"));
		String classnum=request.getParameter("class");
		classnum = classnum.substring(classnum.indexOf("（") + 1, classnum.length() - 1);
		studentBean.setClassnum(classnum);
		AdmDao admDao=new AdmDao();
		boolean flag=admDao.changeStudent(studentBean);
		if(!flag) {
			request.setAttribute("flag", "编辑学生错误！");
		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher("AdmStudent");
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
