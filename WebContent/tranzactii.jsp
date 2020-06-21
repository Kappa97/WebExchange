<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="style.css">
<%@page import="java.sql.*"%>
<%@page import="login.MyConnectionProvider"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tranzactii</title>
</head>
<body>
	<div class="header2">
		<h1>Tranzactii</h1>
	</div>


	<div class="content2">

		<form action="tranzactii" method="post">
			<p>
				Welcome <b>${message}</b> <a href="login.jsp" type="submit"
					name="logout" style="float: right;">Log out</a>
			</p>
			</br>
			<table id="dataTables">
				<thead>
					<tr>
						<th>CodTranzactie&emsp;</th>
						<th>CodClient&emsp;&emsp;</th>
						<th>CodOperator&emsp;&emsp;</th>
						<th>Din&emsp;&emsp;</th>
						<th>In&emsp;&emsp;</th>
						<th>SumaDin&emsp;&emsp;</th>
						<th>SumaIn&emsp;&emsp;</th>
						<th>Coeficientul&emsp;&emsp;</th>
						<th>Data&emsp;&emsp;</th>
					</tr>

					<%
						try {
						Connection con = MyConnectionProvider.getCon();
						PreparedStatement ps = con.prepareStatement("select * from tranzactie");
						PrintWriter printWriter = response.getWriter();
						ResultSet rs = ps.executeQuery();
						System.out.println("in try");
						while (rs.next()) {
					%>
					<tr>
						<th>
							<%
								//rs.getString(1);
							System.out.println("codt");
							out.println(rs.getString(1));
							%>&emsp;&emsp;
						</th>
						
						<th>
							<%
							out.println(rs.getString(2));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(3));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(4));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(5));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(6));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(7));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(8));
							%>&emsp;&emsp;
						</th>
						<th>
							<%
							out.println(rs.getString(9));
							%>&emsp;&emsp;
						</th>

					</tr>
					<%
						}
					} catch (Exception e) {

					}
					%>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="input-group">
				<button type="submit" class="btn" name="back" style="width: 100px">Back</button>
			</div>

		</form>

	</div>
</body>
</html>