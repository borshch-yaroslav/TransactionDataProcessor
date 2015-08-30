<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="include/head-resources.jsp" />

<body>

	<jsp:include page="include/header.jsp" />

	<form method="post" action="addTransactionsFromFile"
		enctype="multipart/form-data">
		File(.xlsx or .csv): <input type="file" required
			name="transactionsFile" accept=".xlsx, .csv" />
		<button>Upload</button>
	</form>

	<div id="message" style="white-space:pre-line">
		<c:out value="${message}" />
	</div>

	<script>
		
	</script>

	<jsp:include page="include/footer.jsp" />
</body>
</html>