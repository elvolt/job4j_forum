<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/0a16ec4483.js" crossorigin="anonymous"></script>
    <title>Вход</title>
</head>
<body>
<div class="container mt-3">
    <div class="row justify-content-center">
        <div class="col-10">
            <h1 class="h4 text-center">Форум job4j</h1>
            <a class="btn btn-primary my-2 float-end" role="button" href="<c:url value='/logout'/>">
                <i class="fas fa-user"></i> ${user.username} |
                <i class="fas fa-door-open"></i> Выйти</a>
            <a href="<c:url value='/'/>">Главная</a> / <c:out value="${post.name}"/>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Автор</th>
                    <th scope="col" style="width:70%">Сообщение</th>
                    <th scope="col">Создано</th>
                </tr>
                </thead>
                <tbody>
                <tr class="bg-light">
                    <td><c:out value="${post.author.username}"/></td>
                    <td><c:out value="${post.desc}"/></td>
                    <td>
                        <fmt:parseDate value="${post.created}" pattern="yyyy-MM-dd'T'HH:mm" var="created"/>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${created}"/>
                    </td>
                </tr>
                <c:forEach items="${post.comments}" var="comment">
                    <tr>
                        <td><c:out value="${comment.user.username}"/></td>
                        <td><c:out value="${comment.text}"/></td>
                        <td>
                            <fmt:parseDate value="${comment.created}" pattern="yyyy-MM-dd'T'HH:mm" var="created"/>
                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${created}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form class="py-2" action=<c:url value='/create_comment'/> method="post">
                <input type="hidden" name="postId" value="${post.id}">
                <div class="mb-3">
                    <label for="comment">Добавить комментарий</label>
                    <textarea class="form-control" name="text" id="comment" cols="30" rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Отправить</button>
            </form>
        </div>
    </div>
</div>
</body>