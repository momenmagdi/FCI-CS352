<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Frind requsts</title>
</head>
<body>



 <form action="/social/responseSeeFriendRequsts" method="post">
  
  <input type="submit" value="See">
  
  </form>
  <form action="/social/acceptRqust" method="post">
  Name : <input type="text" name="fname" /> <br>
   
  <input type="submit" value="Confirm">
  
  </form>
</body>
</html>
