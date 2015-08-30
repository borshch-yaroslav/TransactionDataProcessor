<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="include/head-resources.jsp" />

<body>

	<jsp:include page="include/header.jsp" />

	<div class="ui center aligned container my-container">

		<h2 class="ui header" style="padding-top: 3em">ALL TRANSACTIONS</h2>


		<table class="ui celled table">
			<thead>
				<tr>
					<th>Account</th>
					<th>Description</th>
					<th>Currency</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transaction" items="${transactions}">
					<tr>
						<td>${transaction.account}</td>
						<td>${transaction.description}</td>
						<td>${transaction.currencyCode}</td>
						<td>${transaction.amount}</td>
						<td>
							<form method="post" action="removeTransaction">
								<input type="hidden" name="id" value="${transaction.id}" />
								<button class="ui inverted red button">Remove</button>
							</form>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>


	</div>

	<jsp:include page="include/footer.jsp" />
</body>
</html>