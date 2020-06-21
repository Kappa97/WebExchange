<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.sql.*" %>
	<%@page import="login.MyConnectionProvider"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conturi</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<div class="header">
		<h1>EXCHANGE</h1>
	</div>

	<div class="content">
		<form action="conturi" method="post">
			<p>
				Welcome <b>${message}</b> <a href="login.jsp" type="submit"
					name="logout" style="float: right;">Log out</a>
			</p>
			<div class="select-valuta">
				<label>Selecteaza valuta:</label> <select name="selectvaluta"
					id="selectvaluta">
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
				</select>
				<button type="submit" class="btn" name="verifica"
					style="width: 100px">Selecteaza</button>
			</div>
			<div class="greencolor">
				<label>Valuta selectata este: <b>${valutaSelectata}</b></label><br><b
						style="color: red">${eroare}</b>
			</div>

			<div class="input-group">
				<label>Valuta</label> <input type="text" name="valuta" id="valuta"
					value="${valuta}" >
			</div>

			<div class="input-group">
				<label>Suma</label> <input step="any" type="number" name="suma"
					value="${suma}">
			</div>

			<div class="input-group">
				<label>Coeficientul de cumparare:</label> <input step="any"
					type="number" name="coefc" value="${coefc}">
			</div>

			<div class="input-group">
				<label>Coeficientul de vanzare:</label> <input step="any"
					type="number" name="coefv" id="coefv" value="${coefv}">
			</div>

			<div class="input-group wrapper">
				<button type="submit" class="btn" name="clear"
					onclick="document.getElementById().value=''" style="width: 100px">Clear</button>
				<button type="submit" class="btn" name="sterge" style="width: 100px"
					onclick="return confirm('Esti sigur ca vrei sa stergi valuta')">Sterge</button>
				<button type="submit" class="btn" name="modific"
					style="width: 100px"
					onclick="return confirm('Esti sigur ca vrei sa modifici datele valutei')">Modifica</button>
				<button type="submit" class="btn" name="adauga" style="width: 100px"
					onclick="return confirm('Esti sigur ca vrei sa adugi o valuta noua')">Adauga</button>

			</div>
			<div class="input-group">
				<button type="submit" class="btn" name="back2" style="width: 100px">Back</button>
			</div>

		</form>
	</div>
</body>

</html>