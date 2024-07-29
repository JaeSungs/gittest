<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<title>도서 등록</title>
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
			<h1 class="display-3">도서 등록</h1>
		</div>
	</div>

	<div class="container">

		<!-- 스프링 폼 태그를 사용하면 폼에서 전달되는 파라미터 이름 으로 setter() 메서드를 작성한 클래스의 프로퍼티에 접근 할수 있다.
       	
       	위와 같은 형식으로 사용되며 반드시 태그를 이용하여 JSP페이지 상단에 사용 선언을 해줘야 한다. -->
		<!-- 로그아웃 버튼 추가하기-->
		<div class="float-right">
			<form:form action="${pageContext.request.contextPath}/logout"
				method="POST">
				<!-- action 설정에 pageContext.request.contextPath 는 상대경로 로써 현재 내 위치를 
            	나타낼때 사용한다. localhost:8080/myhome/books/add -->
				<input type="submit" class="btn btn-sm btn-success" value="Logout" />
			</form:form>
		</div>
		<br> <br>
		<!-- 파일 업로드 하기 위해서 form태그 enctype 추가작성 및 action 설정에  CSRF 공격 방지를 위한 토큰 추가 -->
		<form:form modelAttribute="NewBook"
			action="./add?${_csrf.parameterName}=${_csrf.token}"
			class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<legend>${addTitle}</legend>
				<div class="form-group row">
					<label class="col-sm-2 control-label">도서 ID</label>
					<div class="col-sm-3">
						<form:input path="bookId" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">도서명</label>
					<div class="col-sm-3">
						<form:input path="name" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">가격</label>
					<div class="col-sm-3">
						<form:input path="unitPrice" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">저자</label>
					<div class="col-sm-3">
						<form:input path="author" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">상세정보</label>
					<div class="col-sm-5">
						<form:textarea path="description" cols="50" rows="2"
							class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">출판사</label>
					<div class="col-sm-3">
						<form:input path="publisher" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">분야</label>
					<div class="col-sm-3">
						<form:input path="category" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">재고수 </label>
					<div class="col-sm-3">
						<form:input path="unitsInStock" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">출판일</label>
					<div class="col-sm-3">
						<form:input path="releaseDate" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label">상태</label>
					<div class="col-sm-3">
						<form:radiobutton path="condition" value="New" />
						New
						<form:radiobutton path="condition" value="Old" />
						Old
						<form:radiobutton path="condition" value="E-Book" />
						E-Book
					</div>
				</div>
				<!-- 도서 이미지 input태그 작성 -->
				<div class="form-group row">
					<label class="col-sm-2 control-label">도서이미지</label>
					<div class="col-sm-7">
						<form:input path="bookImage" type="file" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value="등록" />
					</div>
				</div>
			</fieldset>
		</form:form>
		<hr>
		<footer>
			<p>&copy; BookMarket</p>
		</footer>
	</div>
</body>
</html>