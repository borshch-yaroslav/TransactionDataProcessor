<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="include/head-resources.jsp" />

<body>

	<jsp:include page="include/header.jsp" />

	<div class="ui center aligned container my-container">

		<h1 class="ui header" style="padding-top: 3em">Wellcome to simple transaction processor</h1>

		<h2 class="ui header" style="margin-top: 2em">Choose file with
			transaction data:</h2>
		<h5 class="ui header">.xlsx or .csv allowed</h5>
		<form method="post" action="addTransactionsFromFile"
			enctype="multipart/form-data">
			<input class="ui red inverted button" type="file" required
				name="transactionsFile" accept=".xlsx, .csv" />
			<button class="ui inverted red button">Upload</button>
		</form>

		<h2 class="ui header">RESULT INFO OF DATA UPLOAD</h2>
		<div id="message" style="white-space: pre-line">
			<h5 class="ui header">
				<c:out value="${message}" />
			</h5>

			<div class="ui styled accordion" style="margin: 0 auto 2em auto">
				<div class="title">
					<i class="dropdown icon"></i> Ignore info
				</div>
				<div class="content">
					<p class="transition hidden">
						<c:out value="${errors}" />
					</p>
				</div>

			</div>

		</div>

	</div>

	<jsp:include page="include/footer.jsp" />
</body>
</html>