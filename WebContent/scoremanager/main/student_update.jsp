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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報更新</h2>
			<%-- パラメーターdoneが存在する場合 --%>
			<c:if test="${!empty done}">
				<div class="bg-success bg-opacity-50 text-center lh-lg">
					<p>${done}</p>
				</div>
			</c:if>
			<form action = "StudentUpdate.action" method="post">
				<div class="mx-3 py-2">
					<div class="my-3">
						<label class="form-label" for="student-ent_year-input">入学年度</label>
						<input class="form-control" type="text" id="student-ent_year-input" name="ent_year"
							value="${ent_year_set}" maxlength="10" readonly />
					</div>
					<div class="my-3">
						<label class="form-label" for="student-no-input">学生番号</label>
						<input class="form-control" type="text" id="student-no-input" name="no"
							value="${no_set}" maxlength="10" readonly />
					</div>
					<div class="my-3">
						<label class="form-label" for="student-name-input">氏名</label>
						<input class="form-control" type="text" id="student-name-input"
							name="name" placeholder="氏名を入力してください" maxlength="10"
							value="${name}" required />
						<div class="mt-2 text-warning">${errors.get("name")}</div>
					</div>
					<div class="my-3">
						<label class="form-label" for="student-class_num-select">クラス</label>
						<select class="form-select" id="student-class_num-select" name="class_num">
							<c:forEach var="num" items="${class_num_set}">
								<%-- 現在のnumと選択されていたclass_numが一致していた場合selectedを追記 --%>
								<option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 form-check text-center">
							<label class="form-check-label" for="student-f3-check">在学中
								<%-- パラメーターf3が存在している場合checkedを追記 --%>
								<input class="form-check-input" type="checkbox"
								id="student-f3-check" name="f3" value="t"
								<c:if test="${!empty f3}">checked</c:if> />
							</label>
						</div>
					<div class="mt-3">
					<!--
						<input class="btn btn-primary" type="submit" value="登録して再度入力" name="continue">
					 -->
						<input class="btn btn-secondary" type="submit" value="登録して終了" name="end">
					</div>
				</div>
			</form>
			<div class="lh-lg row">
				<div class="mx-3 col-1">
					<a href="StudentList.action">戻る</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>