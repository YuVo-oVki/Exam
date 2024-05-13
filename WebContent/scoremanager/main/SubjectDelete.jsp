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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
			<%-- パラメーターdoneが存在する場合 --%>
			<c:if test="${!empty done}">
				<div class="bg-success bg-opacity-50 text-center lh-lg">
					<p>${done}</p>
				</div>
			</c:if>
			<form action = "StudentDeleteExcute.action" method="post">
				<div class="mx-3 py-2">
					<div class="my-3">
						<label class="form-label" for="student-ent_year-input">○○を削除してもよろしいですか？</label>

					</div>
						<input class="btn btn-secondary" type="submit" value="削除" name="end">
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