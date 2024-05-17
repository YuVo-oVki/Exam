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
			<form action = "StudentUpdateExecute.action" method="post">
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
							name="name"  maxlength="10" placeholder="氏名を入力してください"
							value="${name}" required />
						<div class="mt-2 text-warning">${errors.get("name")}</div>
					</div>
					<div class="my-3">
						<label class="form-label" for="student-class_num-select">クラス</label>
						<select class="form-select" id="student-class_num-select" name="class_num">
							<option value="${old_class}">${old_class}</option>
							<c:forEach var="class_num" items="${class_num_set}">
								<option value="${class_num}">${class_num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 form-check text-center">
							<label class="form-check-label" for="student-attend-check">在学中
								<%-- パラメーターf3が存在している場合checkedを追記 --%>
								<input class="form-check-input" type="checkbox"
								id="student-attend-check" name="attend" checked="checked" />
							</label>
						</div>
					<div class="mt-3">
					<!--
						<input class="btn btn-primary" type="submit" value="登録して再度入力" name="continue">
					 -->
						<input class="btn btn-secondary" type="submit" value="変更して終了" name="end">
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