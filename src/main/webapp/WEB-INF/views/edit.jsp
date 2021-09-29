<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <div class="row justify-content-center">
        <div class="col-7">
            <c:if test="${post != null}"><h1 class="h2 text-center mt-3">Редактирование темы</h1></c:if>
            <c:if test="${post == null}"><h1 class="h2 text-center mt-3">Создание темы</h1></c:if>
            <c:if test="${post != null}"><form action="<c:url value='/save?id=${post.id}'/>" method="post"></c:if>
            <c:if test="${post == null}"><form action="<c:url value='/save'/>" method="post"></c:if>
                <div class="mb-3">
                    <label for="name" class="form-label">Тема</label>
                    <input type="text" class="form-control" id="name" name="name"
                           value="<c:out value='${post.name}' default=''/>" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Описание</label>
                    <input type="text" class="form-control" id="description" name="desc"
                           value="<c:out value='${post.desc}' default=''/>" required>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>