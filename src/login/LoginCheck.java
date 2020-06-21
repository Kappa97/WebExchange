package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Operator;
import login.OperatorDAO;
import login.OperatorDAOImpl;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginCheck() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		OperatorDAO operatorDAO = new OperatorDAOImpl();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String submitType = request.getParameter("submit");

		Operator operator = operatorDAO.getOperator(username, password);
		HttpSession session = request.getSession(false);

		if (submitType.equals("login") && operator != null && operator.getNume() != null) {
			
			if(session!=null) {
				// Get the session if exists or create a new one.
				session = request.getSession(true);

				// Set session attributes
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				session.setAttribute("admin", operator.getAdmin());

				request.setAttribute("message", operator.getUsername());
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			}

			else{  
	            out.print("Please login first");  
	            request.getRequestDispatcher("login.jsp").include(request, response);  
	        }  

		} else if (submitType.equals("register")) {

			if (request.getParameter("password").equals(request.getParameter("confirmpassword"))) {

				operator.setNume(request.getParameter("nume"));
				operator.setPrenume(request.getParameter("prenume"));
				operator.setCnp(request.getParameter("cnp"));
				operator.setUsername(request.getParameter("username"));
				operator.setPassword(request.getParameter("password"));
				operator.setAdresa(request.getParameter("adresa"));
				operator.setTelefon(request.getParameter("telefon"));
				operator.setAdmin(request.getParameter("admin"));

				operatorDAO.insertOperator(operator);
				request.setAttribute("message", "Inregistrarea s-a efectuat cu succes!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Inregistrarea a esuat, mai incearca!");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}

		} else if (submitType.equals("logout")) {
			
			session = request.getSession(false);
			request.logout();
			//request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		else {
			request.setAttribute("message", "Nu s-a gasit asemenea operator");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
