<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生・教職員ログイン画面</title>
</head>
<body>

	<h1>学生・教職員ログイン画面</h1>
	
	<%--エラーメッセージ表示用エリア --%>
	<p style ="color: red;">${errorMsg}</p> 
	
	<%-- ログインIDやパスワードの入力エリア --%>
	<form action="HomeServlet" method="post">
		<table>
			<tr>
				<td style="padding-left: 65px">ID: </td>
				<td><input type= "text" name ="id"></td>
	        </tr>
	        <tr>
				<td>パスワード: </td>
				<td><input type= "password" name ="pass"></td>
	        </tr>
		</table>
		<input type ="submit" value="ログイン">
	</form>
	
</body>
</html>