package studentCurriculum;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeaCPasscode
 */
@WebServlet("/TeaCPassword")
public class TeaCPasscode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeaCPasscode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num="";
		String passcode=request.getParameter("num");
		if(passcode!=null) {
			Cookie cookie = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("num"))
						num = cookie.getValue();
				}
			}
			TeaDao teaDao = new TeaDao();
			boolean flag=teaDao.changePasscode(passcode, num);
			if(flag) {
				request.setAttribute("flag", "修改密码成功！新密码为："+passcode);
				
			}
			else {
				request.setAttribute("flag", "修改密码失败！");
			}
		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/WEB-INF/teacpasscode.jsp");
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
