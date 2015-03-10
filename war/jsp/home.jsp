<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Home</title>
</head>
<body>
<p> Welcome  : ${it.name} </p>
<p> This is should be user home page </p>
<p><a href="/social/FrindRequsts">FriendRequsts</a></p>
<form action ="/social/addFriend" method="post">
<input type="submit" value="Add Friend" />
<input type="text" name="email" />
</body>
</html>