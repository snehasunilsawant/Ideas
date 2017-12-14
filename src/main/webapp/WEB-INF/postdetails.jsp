<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>postdetails</title>
</head>
<body>
<a href="/dashboard">Dashboard</a>  |   <a href="/logout">Logout</a>
<h2> <c:out value="${post.users[0].alias}"/> says : <c:out value="${post.text}"/> </h2>


<p>People who liked this post : </p>
	<table>
			<tr>
				<th>Alias</th>
				<th>Name</th>				
			</tr>
			<c:forEach items="${post.plikes}" var="like" >
    		<tr>
    			<td><a href="/user/${like.user.id}"><c:out value="${like.user.alias}"/></a></td>	
    			<td><c:out value="${like.user.name}"/></td>				
			</tr>
			</c:forEach>
			
	</table>

</body>
</html>