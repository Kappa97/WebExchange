package workspace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Conturi
 */
@WebServlet("/conturi")
public class Conturi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String selectValuta;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Conturi() {
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
		ContDAO contDAO = new ContDAOImpl();
		Cont cont;
		String sumaString = (String) request.getParameter("suma");
		String coefcString = (String) request.getParameter("coefv");
		String coefvString = (String) request.getParameter("coefv");

		if (session != null) {
			selectValuta = request.getParameter("selectvaluta");
			cont = contDAO.getCont(selectValuta);
			if (request.getParameter("verifica") != null) {
				session.setAttribute("valutaSelectata", selectValuta);
				request.setAttribute("valuta", cont.getValuta());
				request.setAttribute("suma", cont.getSuma());
				request.setAttribute("coefc", cont.getCoefc());
				request.setAttribute("coefv", cont.getCoefv());
				request.setAttribute("message", name);
				request.getRequestDispatcher("conturi.jsp").forward(request, response);
			}

			if (request.getParameter("modific") != null) {
				if (session.getAttribute("valutaSelectata") != null) {

					if (sumaString.length() > 0 && coefcString.length() > 0 && coefvString.length() > 0) {
						cont.setValuta((String) session.getAttribute("valutaSelectata"));
						cont.setSuma(Double.parseDouble(request.getParameter("suma")));
						cont.setCoefc(Double.parseDouble(request.getParameter("coefc")));
						cont.setCoefv(Double.parseDouble(request.getParameter("coefv")));
						contDAO.updateCont(cont);
						request.setAttribute("message", name);
						session.removeAttribute("valutaSelectata");
						request.setAttribute("eroare", "!!! Valuta a fost modificata !!!");
						request.getRequestDispatcher("conturi.jsp").forward(request, response);
						System.out.println("valuta este selctata");
					} else {
						request.setAttribute("valuta", (String) session.getAttribute("valutaSelectata"));
						request.setAttribute("suma", request.getParameter("suma"));
						request.setAttribute("coefc", request.getParameter("coefc"));
						request.setAttribute("coefv", request.getParameter("coefv"));
						request.setAttribute("eroare", "!!! Trebuie sa fie completate toate campurile !!!");
						request.setAttribute("message", name);
						request.getRequestDispatcher("conturi.jsp").forward(request, response);

					}

				} else {
					request.setAttribute("eroare", "!!! Selecteaza mai inati o valuta pentru a o modifica!!!");
					request.getRequestDispatcher("conturi.jsp").forward(request, response);
					System.out.println("selecteaza valuta");
				}
			}

			if (request.getParameter("clear") != null) {
				session.removeAttribute("valutaSelectata");
				request.setAttribute("message", name);
				request.getRequestDispatcher("conturi.jsp").forward(request, response);
				System.out.println("clear");
			}

			if (request.getParameter("adauga") != null) {
				if (session.getAttribute("valutaSelectata") == null) {
					cont.setValuta(request.getParameter("valuta"));
					cont.setSuma(Double.parseDouble(request.getParameter("suma")));
					cont.setCoefc(Double.parseDouble(request.getParameter("coefc")));
					cont.setCoefv(Double.parseDouble(request.getParameter("coefv")));
					contDAO.insertCont(cont);
					request.setAttribute("message", name);
					request.setAttribute("eroare", "!!! A fost inserat un cont nou !!!");
					request.getRequestDispatcher("conturi.jsp").forward(request, response);
					System.out.println("a fost inserat un cont");
				} else {
					System.out.println("trebuie sa stergi contul");
					request.setAttribute("eroare", "!!! Apasa mai intai pe Clear daca doresti sa adugi o valuta noua !!!");
					request.setAttribute("message", name);
					request.getRequestDispatcher("conturi.jsp").forward(request, response);
				}
			}

			if (request.getParameter("back2") != null) {
				session.removeAttribute("valutaSelectata");
				request.setAttribute("message", name);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			if (request.getParameter("sterge") != null) {
				if (session.getAttribute("valutaSelectata") != null) {
					String valuta = (String) session.getAttribute("valutaSelectata");
					contDAO.deleteCont(valuta);
					session.removeAttribute("valutaSelectata");
					request.setAttribute("eroare", "!!! Contul a fost sters !!!");
					request.getRequestDispatcher("conturi.jsp").forward(request, response);
					System.out.println("contul a fost sters");
				} else {
					session.removeAttribute("valutaSelectata");
					request.setAttribute("message", name);
					request.setAttribute("eroare", "!!! Selecteaza valuta pe care doresti sa o stergi !!!");
					request.getRequestDispatcher("conturi.jsp").forward(request, response);
					System.out.println("selecteaza o valuta");
				}
			}

		}

	}

}
