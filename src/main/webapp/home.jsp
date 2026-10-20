<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h1>ログインだお</h1>
	
	<form action="HomeServlet" method="post">
	ID:<input type="text" name="ID"><br>
	パスワード:<input type="text" name="pass"><br>
	<input type="submit" valu="送信">
	</form>
</body>
</html>