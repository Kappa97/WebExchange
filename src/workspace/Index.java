package workspace;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.MyConnectionProvider;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	static String valuta1;
	static String valuta2;
	static String valoareInputString;
	static int valoareInputInt;
	static Double valoareaCal;
	static Double coefc;
	static Double coefv;

	public Index() {
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
		System.out.println(request.getParameter("param"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("username");
		if (session != null) {

			if (session.getAttribute("username") != null) {
				
				valuta1 = (String) request.getParameter("comboBox1");
				ContDAO contDAO1 = new ContDAOImpl();
				Cont cont1 = contDAO1.getCont(valuta1);
				Double suma1 = cont1.getSuma();

				valuta2 = (String) request.getParameter("comboBox2");
				ContDAO contDAO2 = new ContDAOImpl();
				Cont cont2 = contDAO2.getCont(valuta2);
				Double suma2 = cont2.getSuma();

				

				if (request.getParameter("calculeaza") != null) {
					request.setAttribute("message", name);
					session.removeAttribute("valuta1");
					session.removeAttribute("valuta2");
					session.removeAttribute("valoareInputInt");
					session.removeAttribute("valCalculata");

					if (!valuta1.equals(valuta2) && (valuta1.equals("RON") || valuta2.equals("RON"))) {

						try {
							valoareInputString = request.getParameter("valuta1");
							valoareInputInt = Integer.parseInt(valoareInputString);

							coefc = cont1.getCoefc();
							coefv = cont2.getCoefv();
							Double coeficientul = coefc/coefv; 
							session.setAttribute("coeficientul", coeficientul);

							double valCalculata = valoareInputInt * coeficientul;

							session.setAttribute("valoareInputInt", valoareInputInt);
							session.setAttribute("valCalculata", valCalculata);
							
							//modificarile mele
							session.setAttribute("valuta1", valuta1);
							session.setAttribute("valuta2", valuta2);
							
							request.setAttribute("valuta1", valuta1);
							request.setAttribute("valuta2", valuta2);
							request.setAttribute("valInput", valoareInputInt);
							request.setAttribute("valCalculata", valCalculata);
							request.setAttribute("message", name);
							request.getRequestDispatcher("index.jsp").forward(request, response);
						} catch (Exception e) {

							int valCalculata = 0;
							request.setAttribute("valCalculata", valCalculata);
							request.setAttribute("message", name);
							request.setAttribute("eroare", "!!! Introduceti suma pe care doriti sa o schimbati !!!");
							request.getRequestDispatcher("index.jsp").forward(request, response);

						}
					}
					else {
						request.setAttribute("eroare", "!!! Puteti schimba doar din RON in VALUTA sau invers !!!");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}

				}
			}

			if (request.getParameter("clear") != null) {
				request.setAttribute("message", name);
				try {
					int valCalculata = 0;
					session.removeAttribute("valuta1");
					session.removeAttribute("valuta2");
					session.removeAttribute("valoareInputInt");
					session.removeAttribute("valCalculata");
					request.setAttribute("valCalculata", valCalculata);
					request.setAttribute("message", name);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("message", name);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}

			}
			if (request.getParameter("logout") != null) {
				response.setContentType("text/html");
				session.removeAttribute("username");
				session.removeAttribute("password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
			if (request.getParameter("schimba") != null) {
				request.setAttribute("message", name);
				if (session.getAttribute("valCalculata") != null) {
					request.getRequestDispatcher("schimb.jsp").forward(request, response);
				}else {
					request.setAttribute("message", name);
					request.setAttribute("eroare", "!!! Trebuie sa faci mai intai calculul !!!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
			}
			if(request.getParameter("operatori")!= null) {
				request.setAttribute("message", name);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			if (request.getParameter("conturi") != null) {
				request.setAttribute("message", name);
				request.getRequestDispatcher("conturi.jsp").forward(request, response);
			}
			if(request.getParameter("tranzactii") != null) {
				request.setAttribute("message", name);
				request.getRequestDispatcher("tranzactii.jsp").forward(request, response);
			}
			

		}
		request.setAttribute("message", name);
	}

}
