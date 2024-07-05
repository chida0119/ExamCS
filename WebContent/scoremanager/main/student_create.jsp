<%-- 学生登録JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

			<form action="StudentCreate.action" method="post">
				<label>入学年度</label>
					<select name="ent_year">
						<option value="0">----</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
					<br>
				<label>学生番号</label>
					<input type="text" name="no" value="${no}" maxlength="10" placeholder="学生番号を入力してください" required>
					<br>
				<label>氏名</label>
					<input type="text" name="no" value="${name}" maxlength="30" placeholder="氏名を入力してください" required>
					<br>
				<label>クラス</label>
					<select name="class_num">
						<c:forEach var="class_no" items="${class_select}">
							<option value="${class_no}">${class_no}</option>
						</c:forEach>
					</select>
					<br>
				<button name="end">登録して終了</button>
				<br>
				<a href="StudentList.action">戻る</a>
			</form>
		</section>
	</c:param>
</c:import>

