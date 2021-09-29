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
    <title>Вход</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-4 mt-3">
            <c:if test="${not empty errorMessage}">
                <div class="" style="color:#ff0000; font-weight: bold; margin: 30px 0px;">
                        ${errorMessage}
                </div>
            </c:if>
            <form name='login' action="<c:url value='/login'/>" method='POST'>
                <div class="mb-3">
                    <label for="username" class="form-label">Имя</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Войти</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <a class="float-end" href="<c:url value='/reg'/>">Зарегистрироваться</a>
        </div>
    </div>
</div>
</body>
</html>