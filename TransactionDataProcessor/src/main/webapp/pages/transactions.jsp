<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="include/head-resources.jsp" />

<body>

	<jsp:include page="include/header.jsp" />

	<h1>All transactions</h1>
	<c:forEach var="transaction" items="${transactions}">
    Id: ${transaction.id} Account: ${transaction.account}<br />

		<form method="post" action="removeTransaction">
			<input type="hidden" name="id" value="${transaction.id}" />
			<button>Remove</button>
		</form>
	</c:forEach>

	<script>
		
	</script>

	<jsp:include page="include/footer.jsp" />
</body>
</html>