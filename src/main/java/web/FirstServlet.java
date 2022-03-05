package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init() throws ServletException {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath();
		if(path.equals("/index.do")) {
			HttpSession session=request.getSession(true);
			String username=(String)session.getAttribute("username");
			if(username==null)
			request.getRequestDispatcher("index.html").forward(request, response);
			else{
				request.setAttribute("username", username);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}
		else if(path.equals("/logout.do")) {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=request.getServletPath();
		if(path.equals("/authenticate.do")) {
			HttpSession session=request.getSession(true);
			session.setAttribute("username", request.getParameter("username"));
			response.sendRedirect("index.do");
		}
	}

}
