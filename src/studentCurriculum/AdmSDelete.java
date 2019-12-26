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
 * Servlet implementation class AdmSDelete
 */
@WebServlet("/AdmSDelete")
public class AdmSDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdmSDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdmDao admDao=new AdmDao();
		ArrayList<StudentBean>sList=new ArrayList<>();
		sList=admDao.getStudent();
		int i=Integer.parseInt(request.getParameter("delete"));
		boolean flag=admDao.deleteStudent(sList, i);
		if(!flag) {
			request.setAttribute("flag", "删除学生错误!");
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
