<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="login.MyConnectionProvider"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="header">
		<h2>Register</h2>
	</div>
	<div class="content">
		<form method="post" action="register">
			<p>
				Welcome <b>${message}</b> <a href="login.jsp" type="submit"
					name="logout" style="float: right;">Log out</a>
			</p>

			<div class="select-valuta">
				<label>Selecteaza Operator:</label> <select name="selectoperator"
					id="selectoperator">
					<%
						try {
						Connection conn = MyConnectionProvider.getCon();
						String query = "Select * from operator";
						Statement stm = conn.createStatement();
						ResultSet rs = stm.executeQuery(query);

						while (rs.next()) {
					%>
					<option value="<%=rs.getString("username")%>"><%=rs.getString("username")%></option>
					<%
						}
					} catch (Exception e) {

					}
					%>
				</select>
				<button type="submit" class="btn" name="verificao"
					style="width: 100px">Selecteaza</button>
			</div>

			<div class="greencolor">
				<label>Operatorul selectat este: <b>${usernameOperator}</b></label><br><b
						style="color: red">${eroare}</b>
			</div>
			<div class="input-group">



				<label>Nume</label> <input type="text" name="nume" value="${nume}">
				<br> <label>Prenume</label> <input type="text" name="prenume"
					value="${prenume}"> <br> <label>CNP</label> <input
					type="text" name="cnp" value="${cnp}"> <br> <label>Username</label>
				<input type="text" name="username" value="${usernameOperator}"> <br>
				<label>Password</label> <input type="password" name="password">
				<br> <label>Confirm password</label> <input type="password"
					name="confirmpassword"> <br> <label>Adresa</label> <input
					type="text" name="adresa" value="${adresa}"> <br> <label>Telefon</label>
				<input type="text" name="telefon" value="${telefon}"> <br>
				<label>Admin(1/0)</label> <input type="text" name="admin"
					value="${admin}"> <br>
			</div>
			<div class="input-group wrapper">
				<button type="submit" class="btn" name="clearo"
					onclick="document.getElementById().value=''" style="width: 100px">Clear</button>
				<button type="submit" class="btn" name="stergeo"
					style="width: 100px"
					onclick="return confirm('Esti sigur ca vrei sa stergi Operatorul')">Sterge</button>
				<button type="submit" class="btn" name="modific_operator"
					style="width: 100px"
					onclick="return confirm('Esti sigur ca vrei sa modifici datele Operatorului')">Modifica</button>
				<button type="submit" class="btn" name="reg_user"
					style="width: 100px"
					onclick="return confirm('Esti sigur ca vrei sa adugi un nou Operator')">Register</button>
				<br>
			</div>
			<div class="input-group">
				<button type="submit" class="btn" name="back3"
					style="width: 100px; margin: 0px, 20px">Back</button>
			</div>
		</form>

	</div>
</body>
</html>