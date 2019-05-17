<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books Manager</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5" style="font-size: 24px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Author</td>
        <td>Date</td>
        <td>Price</td>
        <td>Delete</td>
        <td>Update</td>
    </tr>
    <c:forEach items="${requestScope.bookList}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.date}</td>
            <td>${book.price}</td>
            <td>
                <a href="<c:url value="/delete?id=${book.id}"/>">Delete</a>
            </td>
            <td>
                <a href="<c:url value="/update?id=${book.id}"/>">Update</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br>
<br>

<form method="post" action="<c:url value='/add_user'/> ">
    <label><input type="text" name="name"></label>Name<br>
    <label><input type="text" name="author"></label>Author<br>
    <label><input type="date" name="date"></label>Date<br>
    <label><input type="text" name="price"></label>Price<br>

    <input type="submit" value="add" name="add">
</form>
</body>
</html>