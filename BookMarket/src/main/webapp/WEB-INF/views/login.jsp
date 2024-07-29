<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>로그인</title>
</head>
<body>
<nav class="navbar navbar-expand  navbar-dark bg-dark">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="./home">Home</a>
            </div>
        </div>
    </nav>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">로그인</h1>
        </div>
    </div>
    <div class="container col-md-4">
        <div class="text-center">
            <h3 class="form-signin-heading">Please sign in</h3>
        </div>
        <!-- c:if 태그는 if문 태그 입니다 -->
        <!-- 만약 model 로 전송된 error 키값이 있다면 아래 div 태그 안의 내용을 수행. -->
        <c:if test="${not empty error}">  
            <div class="alert alert-danger">
                UserName과 Password가 올바르지 않습니다.<br />
            </div>
        </c:if> 
        <!-- 스프링 폼 태그가 아닌 일반적인 폼태그 사용 -->
        <form class="form-signin" action="<c:url value="/login"/>"    method="post"> 
            <div class="form-group row">
                <input type="text" name="username" class="form-control" placeholder="User Name" required autofocus> 
            </div>
            <div class="form-group row">
                <input type="password" name="password" class="form-control"  placeholder="Password" required>  
            </div>
            <div class="form-group row">
                <button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>                
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  
            </div>
        </form>
    </div>
</body>
</html>