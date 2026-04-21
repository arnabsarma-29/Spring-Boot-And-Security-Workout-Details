<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html lang = "en">
	<head>
		<meta charset = "UTF-8">
		<meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
		<title>Login</title>
		<style>
			:root
			{
				--primary: #6366f1;
				--primary-hover: #4f46e5;
				--dark: #1e293b;
				--error: #ef4444;
				--bg-gradient: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
			}
			body
			{
				margin: 0;
				font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
				background: var(--bg-gradient);
				display: flex;
				justify-content: center;
				align-items: center;
				height: 100vh;
			}
			.login-card
			{
				background: white;
				padding: 40px;
				border-radius: 20px;
				box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1);
				width: 100%;
				max-width: 360px;
				text-align: center;
				box-sizing: border-box;
			}
			h2
			{
				color: var(--dark);
				margin-bottom: 24px;
				font-size: 1.75rem;
			}
			.input-field
			{
				width: 100%;
				padding: 12px 16px;
				margin: 8px 0;
				border: 1px solid #e2e8f0;
				border-radius: 10px;
				font-size: 14px;
				transition: all 0.2s;
				box-sizing: border-box;
			}
			.input-field:focus
			{
				outline: none;
				border-color: var(--primary);
				box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
			}
			.login-btn
			{
				width: 100%;
				padding: 14px;
				background-color: var(--primary);
				color: white;
				border: none;
				border-radius: 10px;
				font-weight: 600;
				font-size: 16px;
				cursor: pointer;
				margin-top: 10px;
				transition: background 0.2s;
			}
			.login-btn:hover
			{
				background-color: var(--primary-hover);
			}
			.divider
			{
				margin: 20px 0;
				display: flex;
				align-items: center;
				color: #94a3b8;
				font-size: 0.85rem;
			}
			.divider::before,
			.divider::after
			{
				content: "";
				flex: 1;
				height: 1px;
				background: #e2e8f0;
				margin: 0 10px;
			}
			.github-btn
			{
				display: flex;
				align-items: center;
				justify-content: center;
				gap: 12px;
				background-color: #24292e;
				color: white;
				padding: 12px;
				border-radius: 10px;
				text-decoration: none;
				font-weight: 500;
				transition: opacity 0.2s;
			}
			.github-btn:hover
			{
				opacity: 0.9;
			}
			.github-logo
			{
				width: 20px;
				height: 20px;
				filter: invert(1);
			}
		</style>
	</head>
	<body>
		<div class = "login-card">
			<h2>Welcome Back</h2>
			<c:if test = "${param.logout != null}">
				<p style = "color: green;">You have been logged out successfully.</p>
			</c:if>
			<form action = "${pageContext.request.contextPath}/perform_login" method = "post">
				<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
				<input type = "text" name = "username" placeholder = "Username" class = "input-field" required>
				<input type = "password" name = "password" placeholder = "Password" class = "input-field" required>
				<button type = "submit" class = "login-btn">Sign In</button>
			</form>
			<c:if test = "${param.error != null}">
				<p style = "color: var(--error);">Invalid username or password.</p>
			</c:if>
			<div class = "divider">OR</div>
			<a href = "${pageContext.request.contextPath}/oauth2/authorization/github" class = "github-btn">
				<img src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" class = "github-logo">
				Continue with GitHub
			</a>
		</div>
	</body>
	</html>