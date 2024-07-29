<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>도서 목록</title>
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
            <h1 class="display-3">도서 목록</h1>
        </div>
    </div>
    <div class="container">
        <div class="row" align="center">
        	<!-- forEach 반복문을 사용하여 출력 합니다. 
        	model에 담겨서 전달된 bookList 데이터를 var 속성값 book으로 다시 정의 합니다. -->
            <c:forEach items="${bookList}" var="book"> 
                <div class="col-md-4">
           			<!-- 도서 이미지 출력하기 -->
                	<img src="<c:url value="/resources/images/${book.bookId}.png"/>" style="width:60%"/>
                	<!-- book을 이용하여 데이터를 출력 합니다. -->
                    <h3>${book.name}</h3>
                    <p>${book.author}
                        <br> ${book.publisher} | ${book.releaseDate}
                    <p align=left>${fn:substring(book.description, 0, 100)}...
                    <p>${book.unitPrice}원
                    
                    <!-- 도서 상세페이지로 이동하기 위해서 내용추가 -->
                    <p><a href="<c:url  value="/books/book?id=${book.bookId}"/>" class="btn btn-Secondary" role="button">상세정보 &raquo;</a>
                </div>
            </c:forEach> 
        </div>
        <hr>
        <footer>
            <p>&copy; BookMarket</p>
        </footer>
    </div>
</body>
</html>