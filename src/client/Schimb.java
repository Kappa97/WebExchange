package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.IOP.Codec;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import login.MyConnectionProvider;
import login.OperatorDAO;
import login.OperatorDAOImpl;
import workspace.Cont;
import workspace.ContDAO;
import workspace.ContDAOImpl;

/**
 * Servlet implementation class Schimb
 */
@WebServlet("/schimb")
public class Schimb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Connection con;
	static PreparedStatement ps;
	static ResultSet rs;
	static String cnp;
	static Client client;
	static String valuta1;
	static String valuta2;
	static Integer suma1;
	static Double suma2;
	static Cont cont1, cont2;
	static int codClient;
	static int codOperator;
	static Double coeficientul;
	static String date;

	public Schimb() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		ClientDAO clientDAO = new ClientDAOImpl();
		PrintWriter out = response.getWriter();
		Client client = clientDAO.getClient(cnp);
		String name = (String) session.getAttribute("username");
		ContDAO contDAO1 = new ContDAOImpl();
		ContDAO contDAO2 = new ContDAOImpl();
		OperatorDAO operatorDAO = new OperatorDAOImpl();
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		if (session != null) {

			if (request.getParameter("VerificaCnp") != null) {
				cnp = (String) request.getParameter("cnpc");
				client = clientDAO.getClient(cnp);

				if (client.getCnp() != null) {

					session.setAttribute("cnpClient", client.getCnp());

					request.setAttribute("nume", client.getNume());
					request.setAttribute("prenume", client.getPrenume());
					request.setAttribute("cnp", client.getCnp());
					request.setAttribute("adresa", client.getAdresa());
					request.setAttribute("telefon", client.getTelefon());
					request.setAttribute("message", name);
					request.setAttribute("valuta1", valuta1);
					System.out.println("exista client cu asa cnp");
					request.getRequestDispatcher("schimb.jsp").forward(request, response);
					//session.removeAttribute("cnpClient");
				} else {

					session.removeAttribute("cnpClinet");
					request.setAttribute("cnp", cnp);
					System.out.println("nu exista client cu asa cnp");
					request.setAttribute("message", name);
					request.getRequestDispatcher("schimb.jsp").forward(request, response);
				}
			}
			if (request.getParameter("schimbac") != null) {
				//removesession 
				 cnp = (String) request.getParameter("cnpc");
				 client = clientDAO.getClient(cnp);

				valuta1 = (String) session.getAttribute("valuta1");
				valuta2 = (String) session.getAttribute("valuta2");
				suma1 = (Integer) session.getAttribute("valoareInputInt");
				suma2 = (Double) session.getAttribute("valCalculata");
				coeficientul = (Double) session.getAttribute("coeficientul");

				System.out.println(suma1 + " " + valuta1 + " " + suma2 + " " + valuta2);
				

				if (session.getAttribute("cnpClient") != null) {
					
					cont1 = contDAO1.getCont(valuta1);
					cont1.setSuma(cont1.getSuma() - suma1);
					contDAO1.updateCont(cont1);

					cont2 = contDAO2.getCont(valuta2);
					cont2.setSuma(cont2.getSuma() + suma2);
					contDAO1.updateCont(cont2);

					codClient = clientDAO.getCodClient(cnp);
					codOperator = operatorDAO.getCodOperator(name);

					date = simpleDateFormat.format(new Date());
					// introduce tranzactia in baza de date
					try {
						con = MyConnectionProvider.getCon();
						ps = con.prepareStatement(
								"insert into tranzactie (client_codc, operator_codo, id_din, id_in, sum_din, sum_in, curs_curent, datat) values(?, ?, ?, ?, ?, ?, ?, ?)");
						ps.setInt(1, codClient);
						ps.setInt(2, codOperator);
						ps.setString(3, valuta1);
						ps.setString(4, valuta2);
						ps.setDouble(5, suma1);
						ps.setDouble(6, suma2);
						ps.setDouble(7, coeficientul);
						ps.setString(8, date);

						ps.executeUpdate();
						con.close();

					} catch (Exception e) {

						System.out.println("ceva nu este in regula 1");
					}

					System.out.println("cnp din sesiune: " + session.getAttribute("cnpClient"));
					session.removeAttribute("cnpClient");
					System.out.println("cnp din sesiune dupa ce la sters: " + session.getAttribute("cnpClient"));
					System.out.println(date);
					System.out.println("nu a creat client");
					session.removeAttribute("valCalculata");
					request.setAttribute("message", name);
					request.setAttribute("eroare", "!!! Schimbul s-a efectuat cu succes !!!");
					request.getRequestDispatcher("index.jsp").forward(request, response);

				} else {

					String cnpc = request.getParameter("cnpc");
					String numec = request.getParameter("numec");
					String prenumec = request.getParameter("prenumec");
					String adresac = request.getParameter("adresac");
					String telefonc = request.getParameter("telefonc");

					if (cnpc.length() > 0 && numec.length() > 0 && prenumec.length() > 0 && adresac.length() > 0
							&& telefonc.length() > 0) {
						client.setCnp(request.getParameter("cnpc"));
						client.setNume(request.getParameter("numec"));
						client.setPrenume(request.getParameter("prenumec"));
						client.setAdresa(request.getParameter("adresac"));
						client.setTelefon(request.getParameter("telefonc"));
						clientDAO.insertClient(client);
						System.out.println("a creat client ");
						// operatia de adugare scadere
						cont1 = contDAO1.getCont(valuta1);
						cont1.setSuma(cont1.getSuma() - suma1);
						contDAO1.updateCont(cont1);

						cont2 = contDAO2.getCont(valuta2);
						cont2.setSuma(cont2.getSuma() + suma2);
						contDAO1.updateCont(cont2);


						
						// scoate codclient
						
						codClient = clientDAO.getCodClient(cnpc);
						codOperator = operatorDAO.getCodOperator(name);
						date = simpleDateFormat.format(new Date());
						System.out.println("?????????//////////////");
						System.out.println(codClient);
						System.out.println(codOperator);
						System.out.println(valuta1);
						System.out.println(valuta2);
						System.out.println(suma1);
						System.out.println(suma2);
						System.out.println(coeficientul);
						System.out.println(date);
						
						// introduce tranzactia in baza de date
						try {
							con = MyConnectionProvider.getCon();
							ps = con.prepareStatement(
									"insert into tranzactie (client_codc, operator_codo, id_din, id_in, sum_din, sum_in, curs_curent, datat) values(?, ?, ?, ?, ?, ?, ?, ?)");
							ps.setInt(1, codClient);
							ps.setInt(2, codOperator);
							ps.setString(3, valuta1);
							ps.setString(4, valuta2);
							ps.setDouble(5, suma1);
							ps.setDouble(6, suma2);
							ps.setDouble(7, coeficientul);
							ps.setString(8, date);

							ps.executeUpdate();
							con.close();
							request.setAttribute("message", name);
							request.setAttribute("eroare", "!!! Schimbul s-a efectuat cu succes !!!");
							request.getRequestDispatcher("index.jsp").forward(request, response);
							

						} catch (Exception e) {
							System.out.println("ceva nu este in regula 2");
							request.setAttribute("eroare", "!!! Schimbul a esuat 1 !!!");
							request.getRequestDispatcher("schimb.jsp").forward(request, response);
						}

					} else {
						request.setAttribute("cnp", request.getParameter("cnpc"));
						request.setAttribute("nume", request.getParameter("numec"));
						request.setAttribute("prenume", request.getParameter("prenumec"));
						request.setAttribute("adresa", request.getParameter("adresac"));
						request.setAttribute("telefon", request.getParameter("telefonc"));

						request.setAttribute("message", name);
						request.setAttribute("eroare", "!!! Completati toate campurile !!!");
						request.getRequestDispatcher("schimb.jsp").forward(request, response);
					}

				}

			}
			if (request.getParameter("back") != null) {
				session.removeAttribute("valCalculata");
				session.removeAttribute("cnpClient");
				request.setAttribute("message", name);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		}

	}

}
