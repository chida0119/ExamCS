<%-- 学生変更	JSP --%>
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

			<form action="StudentUpdateExecute.action" method="post">
				<div class="mb-3">
						<label class="form-label" for="student-ent_year-input">入学年度</label>
						<input readonly class="form-control-plaintext ms-3" type="text"
							id="student-ent_year-input" name="ent_year" value="${stu_date.entYear}"/>
				</div>
				<div class="mb-3">
						<label class="form-label" for="student-no-input">学生番号</label>
						<input readonly class="form-control-plaintext ms-3" type="text"
							id="student-no-input" name="no" value="${stu_date.no}"/>
				</div>
				<label>氏名</label>
					<input type="text" name="name" value="${stu_date.name}" maxlength="30" placeholder="氏名を入力してください" required>${name}
					<br>
				<label>クラス</label>
					<select name="class_num">
						<c:forEach var="class_no" items="${class_list}">
							<c:choose>
								<c:when test="${class_no==stu_date.classNum}">
									<option value="${class_no}" selected>${class_no}</option>
								</c:when>
								<c:otherwise>
									<option value="${class_no}" selected>${class_no}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<br>
				<label>在学中</label>
					<input type="checkbox" name="si_attend">
					<br>
				<input type="submit" name="end" value="変更">
				<br>
				<a href="StudentList.action">戻る</a>
			</form>
		</section>
	</c:param>
</c:import>

