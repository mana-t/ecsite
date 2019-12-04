<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>

<form action="http://localhost:8080/EC__site/login" method="post">
<h1 align="center"align="center">ログイン</h1>
<table align="center">
	<tr>
	<th>ログインコード</th>
	<td><input type=text name="login_cd"></td>
	</tr>
	<tr>
	<th>パスワード</th>
	<td><input type=password name="login_pw"></td>
	</tr>
	<tr>
	<td align="center"><input type=submit value="login"></td>
	</tr>
</table>
</form>
</body>
</html>