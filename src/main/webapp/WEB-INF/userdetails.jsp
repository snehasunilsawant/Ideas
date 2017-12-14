<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>userdetails</title>
</head>
<body>
<a href="/dashboard">Dashboard</a>  |   <a href="/logout">Logout</a>

<h3>Name : <c:out value="${user.name}"/> </h3>
<h3>Alias : <c:out value="${user.alias}"/> </h3>
<h3>Email : <c:out value="${user.email}"/> </h3>

<p>Total Number of Posts : <c:out value="${user.posts.size()}"/></p>
<p>Total Number of Likes : <c:out value="${user.plikes.size()}"/> </p>
</body>
</html>