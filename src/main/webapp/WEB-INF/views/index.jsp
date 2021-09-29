<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <script src="https://kit.fontawesome.com/0a16ec4483.js" crossorigin="anonymous"></script>

    <title>Форум job4j</title>
</head>
<body>
<div class="container mt-3">
    <div class="row justify-content-center">
        <div class="col-10">
            <h1 class="h4 text-center">Форум job4j</h1>
            <a class="btn btn-primary my-2" role="button" href="<c:url value='/create'/>">
                <i class="fas fa-plus"></i> Создать тему</a>
            <a class="btn btn-primary my-2 float-end" role="button" href="<c:url value='/logout'/>">
                <i class="fas fa-user"></i> ${user.username} |
                <i class="fas fa-door-open"></i> Выйти</a>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Тема</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Создана</th>
                    <th scope="col">Автор</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${posts}" var="post">
                    <tr>
                        <td>
                            <a href="/${post.id}"><c:out value="${post.name}"/></a>
                        </td>
                        <td><c:out value="${post.desc}"/></td>
                        <td>
                            <fmt:parseDate value="${post.created}" pattern="yyyy-MM-dd'T'HH:mm" var="created"/>
                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${created}"/>
                        </td>
                        <td><c:out value="${post.author.username}"/></td>
                        <td>
                            <c:if test="${user.username == post.author.username}">
                                <a class="btn btn-light" role="button" href="<c:url value='/update?id=${post.id}'/>">
                                    <i class="far fa-edit"></i></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>
