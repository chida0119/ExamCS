<%-- 成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<form action="TestListSubjectExecute.action" method="post">
			<div>科目情報</div>
				<label>入学年度</label>
					<select name="f1">
						<option value="0">----</option>
						<c:forEach var="year" items="${f1}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
						<label>クラス</label>
					<select name="f2">
						<option value="0">----</option>
						<c:forEach var="num" items="${f2}">
							<option value="${num}">${num}</option>
						</c:forEach>
					</select>
					<label>科目</label>
					<select name="f3">
						<option value="0">----</option>
						<c:forEach var="subject" items="${f3}">
							<option value= ${subject.cd}>${subject.name}</option>
						</c:forEach>
						</select>

					<button name="end">検索</button>
					<br>
			</form>
			<form action="TestListStudentExecute.action" method="post">
				<p>学生情報</p>
				<label>学生番号</label>
					<input type="text" name="f4" value="${no}" maxlength="10" placeholder="学生番号を入力してください" required>
				<button name="end">検索</button>
				<br>
			</form>
		</section>
				<!-- 登録処理画面 -->
			<c:choose>
				<c:when test="${tests.size()>0}">
					<div>科目：${sub.name}</div>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>1回</th>
							<th>2回</th>
						</tr>
						<c:forEach var="test" items="${tests}">
							<tr>
								<td>${test.entYear}</td>
								<td>${test.classNum}</td>
								<td>${test.studentNo}</td>
								<td>${test.studentName}</td>
								<td>${test.getPoint(1)}</td>
								<td>${test.getPoint(2)}</td>
							</tr>
						</c:forEach>
					</table>

				</c:when>
				<c:otherwise>
					<div>成績が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
	</c:param>
</c:import>
