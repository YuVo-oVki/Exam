<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charaset=UTF-8"
pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<form method="get" action="?">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2">
						科目情報
					</div>
					<div class="col-2">
						<label class="form-label" for="test-f1-select">入学年度</label>
						<select class="form-select" id="test-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="test-f2-select">クラス</label>
							<select class="form-select" id="test-f2-select" name="f2">
								<option value="0">--------</option>
								<c:forEach var="num" items="${num}">
									<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
									<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
									</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="test-f3-select">科目</label>
							<select class="form-select" id="test-f3-select" name="f3">
								<option value="0">--------</option>
								<c:forEach var="subject" items="${subjects}">
									<%-- 現在のsubject,cdと選択されていたf3が一致していた場合selectedを追記 --%>
									<option value="${subject.cd}" <c:if test="${subject.cd==f3}">selected</c:if>>${subject.cd}</option>
								</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" value="test_list_subject" formaction="TestListSubject.action" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1")}</div>
				</div>
			</form>
			<form method="get" action="?">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2">
						学生情報
					</div>
					<div class="col-4">
						<label class="form-label" for="test-f4-select">学生番号</label>
							<input class="form-select" id="test-f4-select" name="f4">
								<c:forEach var="num" items="${num}">
									<%-- 現在のnumと選択されていたf4が一致していた場合selectedを追記 --%>
									<option value="${num}" <c:if test="${num==f4}">selected</c:if>>${num}</option>
								</c:forEach>
					</div>
					<div class="col-2 text-center">
						<button type="submit" value="test_list_student" formaction="TestListStudent.action" class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f4")}</div>
				</div>
			</form>
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>1回</th>
							<th>2回</th>
						</tr>
						<c:forEach var="student" items="${students}">
							<tr>
									<td>${student.entYear}</td>
									<td>${student.num}</td>
									<td>${student.cd}</td>
									<td>${student.name}</td>
							</tr>
						</c:forEach>
						<c:forEach var="point" items="${points}">
							<th>
								<td>point_${student.cd}</td>
							</th>
						</c:forEach>
					</table>
		</section>
	</c:param>
</c:import>