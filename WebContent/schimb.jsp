<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Schimb</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="header">
		<h1>EXCHANGE</h1>
	</div>

	<div class="content">
		<form method="post" action="schimb">
			<p>
				Welcome <b>${message}</b> <a href="login.jsp" type="submit"
					name="logout" style="float: right;">Log out</a>
			</p>

			<div class="greencolor">
				<p style="size: 16px" name="valuta">Schimba: ${valoareInputInt}
					${valuta1} in ${valCalculata} ${valuta2}</p><br><b
						style="color: red">${eroare}</b>
			</div>
			<div class="input-group">
				<label>CNP</label>
			</div>
			<div class="select-cnp">

				<input type="text" name="cnpc" value="${cnp}">
				<button class="btn" type="submit" name="VerificaCnp"
					style="width: 100px;">Check</button>
			</div>
			<div class="input-group">
				<label>Nume</label> <input type="text" name="numec" value="${nume}">
			</div>
			<div class="input-group">
				<label>Prenume</label> <input type="text" name="prenumec"
					value="${prenume}">
			</div>
			<div class="input-group">
				<label>Adresa</label> <input type="text" name="adresac"
					value="${adresa}">
			</div>
			<div class="input-group">
				<label>Telefon</label> <input type="text" name="telefonc"
					value="${telefon}">
			</div>
			<div class="input-group">
				<button type="submit" class="btn" name="back"
					style="width: 100px; float: left;">Back</button>
			</div>
			<div class="input-group">

				<button type="submit" class="btn" name="schimbac"
					onclick="return confirm('Esti sigur ca vrei sa finisezi Schimbul ?')"
					style="width: 100px; float: right;">OK</button>
			</div>
			<div>
				<br>
			</div>

		</form>


	</div>
</body>
</html>