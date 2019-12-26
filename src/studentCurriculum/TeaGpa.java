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
 * Servlet implementation class TeaGpa
 */
@WebServlet("/TeaGpa")
public class TeaGpa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeaGpa() {
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
		String num = (String) request.getParameter("num");
		String num1=num;
		int flag=0;
		ArrayList<StudentBean> sList = new ArrayList<>();
		if (num != null) {
			num = num.substring(num.indexOf("（") + 1, num.length() - 1);
			
			flag = Integer.parseInt(request.getParameter("order"));
			sList = new ArrayList<>();
			sList = teaDao.getGpa(num, flag);
			if(sList.isEmpty()) {
				request.setAttribute("flag", num1+"查无数据");
			}
			
		}
		request.setAttribute("f", flag);
		ArrayList<String> aList = teaDao.getAllClass();
		request.setAttribute("num2", num1);
		request.setAttribute("alist", aList);
		request.setAttribute("slist", sList);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/teagpa.jsp");
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
