<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%> 
<!-- isELIgnored = "false" 최신이클립스는 자동설정하므로 생략가능 -->
<!-- 페이지 지시자에 "core라이브러리를 사용하겠다"하고 JSTL 선언해야 함. c 접두어로 시작 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 목록</title>

<style type="text/css">
#listForm{
	margin: auto;
	width: 640px;	
	border: 1px solid red;
}

h2{
	text-align:center;
}

table{
	margin: auto;
	width: 550px;
}

.tr_top{
	background-color : lime;
}

.div_empty{	
	width: 100%;
	height: 100%;
	text-align:	center;
	background-color: yellow;
}

.td_command{
	text-align: right;
}

/*각 상품의 이미지 스타일 */
#productImage{
 	width: 150px;
 	height: 150px;
 	border: none;
}

#todayImageList{
	text-align: center;
}

/*장바구니 이미지 스타일 */
#cartImage{
 	width: 70px;
 	height: 70px;
 	border: none;
}

#select{
 	text-align: right;
}

#commandList{
    text-align: center;
}

#upImage{
	width: 15px;
}

#downImage{
	width: 15px;
}
</style>
<script>
	//전체 장바구니 항목의 체크박스를 체크 또는 해제
	function checkAll(theForm) {
		if(theForm.remove.length == undefined) {//장바구니 항목을 선택하는 체크박스가 1개일때
			theForm.remove.checked = theForm.allCheck.checked;
		} else {//장바구니 항목을 선택하는 체크박스가 여러개일때 배열객체
			for (var i = 0 ; i < theForm.remove.length ; i++) {
				theForm.remove[i].checked = theForm.allCheck.checked;
			}
		}
	}
</script>
</head>
<body>
<!-- 검색에 사용되는 startMoney값과 endMoney값을 속성으로 설정하는 부분
이때, 검색작업을 하지않고 장바구니목록보기 페이지가 실행된 경우 이값들이 null이기때문에
해당 속성들을 사용할때 NullPointerException이 발생한다.
따라서 NullPointerException이 발생하지않도록 if문으로 처리해줌 -->
<c:if test="${startMoney != null}">
	<c:set var = "startMoney" value="${startMoney}"></c:set>
</c:if>
<c:if test="${endMoney != null}">
	<c:set var = "endMoney" value="${endMoney}"></c:set>
</c:if>
	<section id = "listForm">
		<c:if test="${cartList != null && cartList.size() > 0}">
			<h2>장바구니 목록</h2>
			<!-- JSP책 767p 그림 -->
			<form method="post">
				<table>
				<!-- 가격별 검색부분 처리(s) -->
					<tr id = "select">
						<td colspan="6">
							<select>
								<option>=최하=</option>
								<c:choose><!-- switch문 -->
									<c:when test="${startMoney == 1000}"><!-- case -->
										<option selected="selected">1000</option>
										<option>2000</option>
										<option>3000</option>
										<option>4000</option>
									</c:when>
									<c:when test="${startMoney == 2000}"><!-- case -->
										<option>1000</option>
										<option selected="selected">2000</option>
										<option>3000</option>
										<option>4000</option>
									</c:when>
									<c:when test="${startMoney == 3000}"><!-- case -->
										<option>1000</option>
										<option>2000</option>
										<option selected="selected">3000</option>
										<option>4000</option>
									</c:when>
									<c:when test="${startMoney == 4000}"><!-- case -->
										<option>1000</option>
										<option>2000</option>
										<option>3000</option>
										<option selected="selected">4000</option>
									</c:when>
								</c:choose>	
								<option>=최고=</option>
								<c:choose><!-- switch문 -->
									<c:when test="${endtMoney == 1000}"><!-- case -->
										<option selected="selected">1000</option>
										<option>2000</option>
										<option>3000</option>
										<option>4000</option>
									</c:when>
									<c:when test="${endMoney == 2000}"><!-- case -->
										<option>1000</option>
										<option selected="selected">2000</option>
										<option>3000</option>
										<option>4000</option>
									</c:when>
									<c:when test="${endMoney == 3000}"><!-- case -->
										<option>1000</option>
										<option>2000</option>
										<option selected="selected">3000</option>
										<option>4000</option>
									</c:when>
									<c:when test="${endMoney == 4000}"><!-- case -->
										<option>1000</option>
										<option>2000</option>
										<option>3000</option>
										<option selected="selected">4000</option>
									</c:when>
								</c:choose>	
							</select>
							<!-- 검색버튼을 클릭하면 최하가격과 최고가격으로 장바구니 항목을 다시검색 -->
							<input type="submit" value="검색" formaction="dogCartSearch.dog"/>					
						</td>
					</tr>
					<!-- 가격별 검색부분 처리(e) -->
					
					<tr class="tr_top">
						<td>
							<input type="checkbox" name="allCheck" onclick="checkAll(this.form)">
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		
		<c:if test="${cartList == null}">
		<section class="div_empty">장바구니가 비어있습니다.</section>
		</c:if>
	</section>
</body>
</html>