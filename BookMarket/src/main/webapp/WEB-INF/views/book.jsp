<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>도서 상세 정보</title>
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
            <h1 class="display-3">도서 정보</h1>
        </div>
    </div>

    <div class="container">
        <div class="row">
        	<!-- 도서상세보기 페이지에서 도서 이미지 출력하기 -->
        	<div class="col-md-4">
        		<c:choose>
                    <c:when test="${book.getBookImage()==null}">
                        <img src="<c:url value="/resources/images/${book.getBookId()}.png"/>" style="width: 100%" />
                    </c:when>
                    <c:otherwise>
                        <img src="<c:url value="/resources/images/${book.getBookImage().getOriginalFilename()}"/>" style="width: 100%" />
                    </c:otherwise>
                </c:choose>
        	</div>
            <div class="col-md-12">
                <h3>${book.name}</h3>  
                <p>${book.description}</p>
                <br>
                <p><b>도서코드 : </b><span class="badge badge-info">${book.bookId}</span>
                <p><b>저자</b> : ${book.author}
                <p><b>출판사</b> : ${book.publisher}
                <p><b>출판일</b> : ${book.releaseDate}
                <p><b>분류</b> : ${book.category}
                <p><b>재고수</b> : ${book.unitsInStock}
                <h4>${book.unitPrice}원</h4>  
                <br>
                <!-- 도서 주문 하기 위해서 추가하기 -->
                 <form:form  name="addForm"  method="put">
                	<p><a href="javascript:addToCart('../cart/add/${book.bookId}')" class="btn btn-primary">도서주문 &raquo;</a> 
               	 	<a href="<c:url value="/cart" />" class="btn btn-warning"> 장바구니 &raquo;</a>
                	<a href="<c:url value="/books"/>" class="btn btn-secondary">도서 목록 &raquo;</a>  
             	</form:form>  
            </div>
        </div>
        <hr>
        <footer>
            <p>&copy; BookMarket </p>
        </footer>
    </div>
</body>
</html>