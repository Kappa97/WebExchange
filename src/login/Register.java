package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String selectOperator;

	public Register() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("username");
		OperatorDAO operatorDAO = new OperatorDAOImpl();
		Operator operator;
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String cnp = request.getParameter("cnp");
		String username = request.getParameter("username");
		String adresa = request.getParameter("adresa");
		String telefon = request.getParameter("telefon");
		String admin = request.getParameter("admin");

		if (session != null) {
			request.setAttribute("message", name);
			selectOperator = request.getParameter("selectoperator");
			operator = operatorDAO.getOperatorUsername(selectOperator);

			if (request.getParameter("verificao") != null) {

				request.setAttribute("nume", operator.getNume());
				request.setAttribute("prenume", operator.getPrenume());
				request.setAttribute("cnp", operator.getCnp());
				request.setAttribute("username", operator.getUsername());
				session.setAttribute("usernameOperator", operator.getUsername());
				request.setAttribute("adresa", operator.getAdresa());
				request.setAttribute("telefon", operator.getTelefon());
				request.setAttribute("admin", operator.getAdmin());
				request.setAttribute("message", name);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}

			if (request.getParameter("modific_operator") != null) {
				if (session.getAttribute("usernameOperator") != null) {
					// daca introduc si parola
					if (request.getParameter("password").equals(request.getParameter("confirmpassword"))
							&& (Integer) request.getParameter("password").length() > 0) {

						if (nume.length() > 0 && prenume.length() > 0 && cnp.length() > 0 && username.length() > 0
								&& adresa.length() > 0 && telefon.length() > 0 && admin.length() > 0) {
							operator.setUsername((String) session.getAttribute("usernameOperator"));
							operator.setNume(request.getParameter("nume"));
							operator.setPrenume(request.getParameter("prenume"));
							operator.setCnp(request.getParameter("cnp"));
							operator.setPassword(request.getParameter("password"));
							operator.setAdresa(request.getParameter("adresa"));
							operator.setTelefon(request.getParameter("telefon"));
							operator.setAdmin(request.getParameter("admin"));
							operatorDAO.updateOperatorWithPassword(operator);

							System.out.println("operatorul este selectat cu parola");
							session.removeAttribute("usernameOperator");
							request.setAttribute("eroare", "!!! Operatorul a fost modificat cu success !!!");
							request.setAttribute("message", name);
							request.getRequestDispatcher("register.jsp").forward(request, response);
						} else {
							operator.setUsername((String) session.getAttribute("usernameOperator"));
							request.setAttribute("nume", operator.getNume());
							request.setAttribute("prenume", operator.getPrenume());
							request.setAttribute("cnp", operator.getCnp());
							request.setAttribute("adresa", operator.getAdresa());
							request.setAttribute("telefon", operator.getTelefon());
							request.setAttribute("admin", operator.getAdmin());
							request.setAttribute("message", name);

							session.removeAttribute("usernameOperator");
							request.setAttribute("eroare", "!!! Completeaza toate campurile (password la alegere) !!!");
							request.setAttribute("message", name);
							request.getRequestDispatcher("register.jsp").forward(request, response);
						}
							//daca nu introduc parola
					} else if ((Integer) request.getParameter("password").length() == 0
							&& (Integer) request.getParameter("confirmpassword").length() == 0) {
						//daca completez toate campurile
						if (nume.length() > 0 && prenume.length() > 0 && cnp.length() > 0 && username.length() > 0
								&& adresa.length() > 0 && telefon.length() > 0 && admin.length() > 0) {
							operator.setUsername((String) session.getAttribute("usernameOperator"));
							operator.setNume(request.getParameter("nume"));
							operator.setPrenume(request.getParameter("prenume"));
							operator.setCnp(request.getParameter("cnp"));
							operator.setAdresa(request.getParameter("adresa"));
							operator.setTelefon(request.getParameter("telefon"));
							operator.setAdmin(request.getParameter("admin"));
							operatorDAO.updateOperatorWithoutPassword(operator);
							System.out.println("operatorul este selectat fara parola");
							request.setAttribute("eroare", "!!! Operatorul a fost modificat cu success !!!");
							session.removeAttribute("usernameOperator");
							session.setAttribute("message", name);
							request.getRequestDispatcher("register.jsp").forward(request, response);
						} else {
							//operator.setUsername((String) session.getAttribute("usernameOperator"));
							request.setAttribute("nume", request.getParameter("nume"));
							request.setAttribute("prenume", request.getParameter("prenume"));
							request.setAttribute("cnp", request.getParameter("cnp"));
							request.setAttribute("adresa", request.getParameter("adresa"));
							request.setAttribute("telefon", request.getParameter("telefon"));
							request.setAttribute("admin", request.getParameter("admin"));
							
							request.setAttribute("username", (String) session.getAttribute("usernameOperator"));

							// session.removeAttribute("usernameOperator");
							request.setAttribute("eroare", "!!! Completeaza toate campurile (password la alegere) !!!");
							request.setAttribute("message", name);
							request.getRequestDispatcher("register.jsp").forward(request, response);
						}
						//parola nnu este in regula
					} else {
						request.setAttribute("username", (String) session.getAttribute("usernameOperator"));
						request.setAttribute("nume", request.getParameter("nume"));
						request.setAttribute("prenume", request.getParameter("prenume"));
						request.setAttribute("cnp", request.getParameter("cnp"));
						request.setAttribute("adresa", request.getParameter("adresa"));
						request.setAttribute("telefon", request.getParameter("telefon"));
						request.setAttribute("admin", request.getParameter("admin"));
						
						
						
						request.setAttribute("eroare", "!!! Parola nu este in regula !!!");
						System.out.println("ceva nu este in regula");
						session.removeAttribute("usernameOperator");
						request.setAttribute("message", name);
						request.getRequestDispatcher("register.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("eroare", "!!! Alege operatorul care doriti sa-l modificati !!!");
					System.out.println("alege mai intai un operator");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
			if (request.getParameter("reg_user") != null) {
				if (session.getAttribute("usernameOperator") == null) {

					if (request.getParameter("password").equals(request.getParameter("confirmpassword"))
							&& (Integer) request.getParameter("password").length() > 0) {

						operator.setNume(request.getParameter("nume"));
						operator.setPrenume(request.getParameter("prenume"));
						operator.setCnp(request.getParameter("cnp"));
						operator.setUsername(request.getParameter("username"));
						operator.setPassword(request.getParameter("password"));
						operator.setAdresa(request.getParameter("adresa"));
						operator.setTelefon(request.getParameter("telefon"));
						operator.setAdmin(request.getParameter("admin"));

						operatorDAO.insertOperator(operator);
						request.setAttribute("eroare", "!!! Inregistrarea s-a efectuat cu succes !!!");
						System.out.println("Inregistrarea s-a efectuat cu succes!");
						request.setAttribute("message", name);
						request.getRequestDispatcher("register.jsp").forward(request, response);
					} else {

						request.setAttribute("nume", request.getParameter("nume"));
						request.setAttribute("prenume", request.getParameter("prenume"));
						request.setAttribute("cnp", request.getParameter("cnp"));
						request.setAttribute("username", request.getParameter("username"));
						request.setAttribute("adresa", request.getParameter("adresa"));
						request.setAttribute("telefon", request.getParameter("telefon"));
						request.setAttribute("admin", request.getParameter("admin"));
						request.setAttribute("eroare", "!!! Mai scrieti o data parola !!!");
						System.out.println("Inregistrarea a esuat!");
						request.setAttribute("message", name);
						request.getRequestDispatcher("register.jsp").forward(request, response);
					}

				} else {
					request.setAttribute("eroare", "!!! Apasa mai intai pe clear !!!");
					System.out.println("apsa pe clear");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			}
			if (request.getParameter("clearo") != null) {
				session.removeAttribute("usernameOperator");
				System.out.println("clear all");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			if (request.getParameter("stergeo") != null) {
				if (session.getAttribute("usernameOperator") != null) {
					operatorDAO.deleteOperator((String) session.getAttribute("usernameOperator"));
					request.setAttribute("message", name);
					request.setAttribute("eroare", "!!! Operatorul a fost sters cu succes !!!");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					System.out.println("operatorul a fost sters");

				} else {
					request.setAttribute("message", name);
					request.setAttribute("eroare", "!!! Selecteaza operatorul care doriti sa-l stergeti !!!");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					System.out.println("selecteaza un operator");
				}
			}

			if (request.getParameter("back3") != null) {
				request.setAttribute("message", name);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		}
		// request.getRequestDispatcher("login.jsp").forward(request, response);

	}

}
