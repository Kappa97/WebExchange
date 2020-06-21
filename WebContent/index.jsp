<%@page import="login.MyConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--%@ page import="java.io.*" %>-->
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html" charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<h2>Exchange</h2>
	</div>

	<div class="content">

		<form method="post" action="index">
			<p>
				Welcome <b>${message}</b> <a href="login.jsp" type="submit"
					name="logout" style="float: right;">Log out</a>
			</p>
			<div class="select-valuta">
				<br> <label>Din:</label> <select name="comboBox1">

					<%
						try {
						Connection conn = MyConnectionProvider.getCon();
						String query = "Select * from conturi";
						Statement stm = conn.createStatement();
						ResultSet rs = stm.executeQuery(query);

						while (rs.next()) {
					%>
					<option value="<%=rs.getString("valuta")%>"><%=rs.getString("valuta")%></option>
					<%
						}
					} catch (Exception e) {

					}
					%>
				</select> <input type="number" name="valuta1" id="valuta1"
					style="text-align: right;" value=${valInput}>
			</div>
			<div class="greencolor">
				<p style="color: green;">
					<b>&nbsp;&nbsp;&nbsp;&nbsp;${valuta1}&nbsp;&nbsp;${valuta2}</b><b
						style="color: red">${eroare}</b>
				</p>

			</div>

			<div class="select-valuta">
				<label>In:</label>&nbsp;&nbsp;&nbsp;&nbsp;<select name="comboBox2">
					<%
						try {
						Connection conn = MyConnectionProvider.getCon();
						String query = "Select * from conturi";
						Statement stm = conn.createStatement();
						ResultSet rs = stm.executeQuery(query);

						while (rs.next()) {
					%>
					<option value="<%=rs.getString("valuta")%>"><%=rs.getString("valuta")%></option>
					<%
						}
					} catch (Exception e) {

					}
					%>
				</select> <input readonly type="number" name="valuta2" id="valuta2"
					style="text-align: right;" value=${valCalculata}>

			</div>
			<div class="input-group wrapper">
				<div class="wrapper ">

					<button class="btn" type="submit" name="clear" style="width: 95px">Clear</button>
					<button class="btn" type="submit" name="calculeaza"
						style="width: 95px">Calculeaza</button>

				</div>
			</div>
			<div class="input-group">
				<div class="wrapper">
					<button class="btn" type="submit" name="schimba"
						style="width: 94px; float: right; margin: 0px 0px;">Schimba</button>
				</div>
			</div>
			<%
				if (session.getAttribute("admin").equals("1")) {
			%>
			<div class="input-group">
				<div class="wrapper">

					<button class="btn" type="submit" name="operatori"
						style="width: 94px; float: right; margin: 0px 5px;">Operatori</button>

				</div>
			</div>
			<div class="input-group">
				<div class="wrapper">
					<button class="btn" type="submit" name="conturi"
						style="width: 94px; float: right; margin: 0px 0px;">Banii</button>
				</div>
			</div>

			<%
				System.out.println("este admin");
			}
			%>

			<div class="input-group">
				<div class="wrapper">
					<button class="btn" type="submit" name="tranzactii"
						style="width: 94px; float: right; margin: 0px 5px;">Tranzactii</button>
					<br>
				</div>
			</div>

		</form>


	</div>

</body>

</html>