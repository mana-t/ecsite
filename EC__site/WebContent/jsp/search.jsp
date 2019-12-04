<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索</title>
</head>
<body>

<h1 align="center">検索</h1>

<form action="http://localhost:8080/EC__site/serch">

<p align="center"><input type="text" name="search"></p>
<p align="center">カテゴリ
<select name="category">
	<option value="sample">sample</option>
</select></p>
<p align="center"><input type="submit" value="検索"></p>

</form>

</body>
</html>