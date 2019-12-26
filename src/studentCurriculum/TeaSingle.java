package studentCurriculum;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeaSingle
 */
@WebServlet("/TeaSingle")
public class TeaSingle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaSingle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TeaDao teaDao = new TeaDao();
		String counum = (String) request.getParameter("num");
		request.setAttribute("num",counum);
		String year=(String)request.getParameter("year");
		String num1=counum;
		int flag=0;
		Cookie cookie = null;
		String num="";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("num"))
					num = cookie.getValue();
			}
		}
		ArrayList<StudentBean> sList = new ArrayList<>();
		if (counum != null) {
			counum = counum.substring(counum.indexOf("（") + 1, counum.length() - 1);
			
			flag = Integer.parseInt(request.getParameter("order"));
			sList = new ArrayList<>();
			sList = teaDao.getSingle(num,counum, year,flag);
			if(sList.isEmpty()) {
				request.setAttribute("flag", year+"学年"+num1+"查无数据");
			}
			
		}
		request.setAttribute("year",year);
		request.setAttribute("f", flag);
		ArrayList<String> aList = teaDao.getACourse(num);
		request.setAttribute("alist", aList);
		request.setAttribute("slist", sList);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/teasingle.jsp");
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
