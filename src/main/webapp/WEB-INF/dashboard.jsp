<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<h2>Welcome , ${ alias } ! </h2>
	
	<form:form method="POST" action="/addPost" modelAttribute="post">
	
	<p><form:label path="text">Post : 	
	<form:input placeholder="Post somehing witty here ....       "  path="text"/></form:label> 
	
	<input type="submit" value="Idea!"></p>	
	
	</form:form>
	
	<table>
			<tr>
				<th>User</th>
				<th>Post</th>
				<th>Like</th>				
			</tr>
			
			<tr>
			<c:forEach items="${posts}" var="post" >
    		<tr>    		
					<td><a href="/user/${post.users[0].id}"><c:out value="${post.users[0].alias}"/></a> Says : </td> 
					<td><c:out value="${post.text}"/></td>
					<td>
						<a href="/like/${post.id}">Like</a>
						<a href="/post/${post.id}"><c:out value="${post.plikes.size()} people"/></a>liked this
						<c:if test="${ post.users[0].alias == alias }">
            				<a href="/delete/${ post.id }">Delete</a>
            			</c:if>
					</td>
			</tr>
			</c:forEach>
	</table>
</body>
</html>