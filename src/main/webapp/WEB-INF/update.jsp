<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
    <form method="post" action="<c:url value='/update'/> ">
        <input type="number" hidden name="id"   value="${requestScope.book.id}"/>
        <label><input type="text" name="name"   value="${requestScope.book.name}"></label>Name<br>
        <label><input type="text" name="author" value="${requestScope.book.author}" ></label>Author<br>
        <label><input type="datetime" name="date"   value="${requestScope.book.date}"></label>Date<br>
        <label><input type="text" name="price"  value="${requestScope.book.price}"></label>Price<br>

        <input type="submit" value="update" name="update">
    </form>
</body>
</html>
