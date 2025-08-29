<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid bg-light py-2">
	<div class="row">
		<div class="col-12">
			<ul class="nav justify-content-end align-items-center">
				<c:choose>
					<c:when test="${sessionScope.account == null}">
						<li class="nav-item"><a class="nav-link text-dark"
							href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
						<li class="nav-item"><a class="nav-link text-dark"
							href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link text-dark"
							href="${pageContext.request.contextPath}/member/myaccount">
								${sessionScope.account.fullName} </a></li>
						<li class="nav-item"><a class="nav-link text-dark"
							href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item"><a class="nav-link text-dark" href="#"
					id="search-icon"> <i class="fas fa-search"></i>
				</a></li>
			</ul>
		</div>
	</div>
</div>

<style>
.nav-link {
	font-weight: 500;
	transition: color 0.3s;
}

.nav-link:hover {
	color: #007bff !important;
}

#search-icon {
	font-size: 1.2rem;
}

@media ( max-width : 576px) {
	.nav-link {
		font-size: 0.9rem;
	}
}
</style>