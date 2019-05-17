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
    </tr>
    <c:forEach items="${requestScope.bookList}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.date}</td>
            <td>${book.price}</td>
        </tr>
    </c:forEach>
</table>

<br>
<br>

<form method="post" action="">
    <label><input type="number" name="id"></label>Id<br>
    <label><input type="text" name="name"></label>Name<br>
    <label><input type="text" name="author"></label>Author<br>
    <label><input type="date" name="date"></label>Date<br>
    <label><input type="text" name="price"></label>Price<br>
    <input type="submit" value="ok" name="ok">
    <input type="submit" value="delete" name="delete">
    <input type="submit" value="update" name="update"><br>
</form>
</body>
</html>
