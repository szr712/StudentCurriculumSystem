package studentCurriculum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdmTDelete
 */
@WebServlet("/AdmTDelete")
public class AdmTDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmTDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdmDao admDao=new AdmDao();
		ArrayList<TeacherBean>tList=new ArrayList<>();
		tList=admDao.getTeacher();
		int i=Integer.parseInt(request.getParameter("delete"));
		boolean flag=admDao.deleteTeacher(tList, i);
		if(!flag) {
			request.setAttribute("flag", "删除教师错误!");
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
