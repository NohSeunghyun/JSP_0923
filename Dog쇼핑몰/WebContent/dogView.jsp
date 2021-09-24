<!-- 개 하나의 상세정보를 출력하는 View페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%><!-- EL언어 사용하려면 페이지지시자 안에 코드를 넣어줘야 함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#viewForm {
		margin: 0 auto;
		width: 640px;
		border: 1px solid red;
	}
	
	h2 {
		text-align: center;
	}
	
	img {
		width: 280px;
		height: 280px;
		border: none;
	}
	
	#content_main {
		height: 300px;
	}
	
	#content_left {
		width: 300px;
		float: left;
	}
	
	#content_right {
		width: 340px;
		float: left;
	}
	
	#desc {
		height: 170px;
		background: skyblue;
	}
	
	#commendList {
		text-align: center;
	}
</style>
</head>
<!-- JSP책-752p-두번째그림 -->
<body>
	<section id="viewForm">
		<h2>${dog.kind}의 상세정보</h2>
		<section id="content_main">
			<section id="content_left">
				<img src="images/${dog.image}">
			</section>
			<section id="content_right">
				<b>품종 : </b> ${dog.kind} <br>
				<b>가격 : </b> ${dog.price} <br>
				<b>신장 : </b> ${dog.height} <br>
				<b>체중 : </b> ${dog.weight} <br>
				<b>원산지 : </b> ${dog.country} <br>
				<p id="desc">
					<b>내용 : </b> ${dog.content} <br>
				</p>
			</section>
		</section>
		<div style="clear:both;"></div>
		<nav id="commendList">
			<a href="dogList.dog">쇼핑 계속하기</a>
			<a href="dogCartAdd.dog?id=${dog.id}">장바구니에 담기</a>
		</nav>
	</section>
</body>
</html>