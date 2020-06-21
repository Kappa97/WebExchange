<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Web Exchange</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<div class="header">
		<h2>Login</h2>
	</div>
	<div class="content">
		<form method="post" action="loginCheck">
			<p style="red">${message}<p>
			<div class="input-group">
				<label>Username</label> <input type="text" name="username">
				<br> <label>Password</label> <input type="password"
					name="password"> <br>
				<button type="submit" name="submit" value="login">Login</button><br>
			
			</div>
		</form>

	</div>

</body>
</html>