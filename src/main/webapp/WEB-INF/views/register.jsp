<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
	<!DOCTYPE html>
	<html lang = "en">
	<head>
		<meta charset = "UTF-8">
		<meta name = "viewport" content = "width=device-width, initial-scale=1.0">
		<title>Create Account</title>
		<style>
			:root
			{
				--primary: #6366f1;
				--primary-hover: #4f46e5;
				--dark: #1e293b;
				--bg-gradient: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
				--error: #ef4444;
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
			.register-card
			{
				background: white;
				padding: 40px;
				border-radius: 20px;
				box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1);
				width: 100%;
				max-width: 400px;
				text-align: center;
				box-sizing: border-box;
			}
			h2
			{
				color: var(--dark);
				margin-bottom: 8px;
				font-size: 1.75rem;
			}
			p
			{
				color: #64748b;
				font-size: 0.9rem;
				margin-bottom: 24px;
			}
			.input-field
			{
				width: 100%;
				padding: 12px 16px;
				margin: 8px 0;
				border: 1px solid #e2e8f0;
				border-radius: 10px;
				font-size: 14px;
				box-sizing: border-box;
				transition: all 0.2s;
			}
			.input-field:focus
			{
				outline: none;
				border-color: var(--primary);
				box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
			}
			.role-container-label
			{
				display: block;
				text-align: left;
				font-size: 0.85rem;
				color: #64748b;
				margin: 10px 0 5px 5px;
				font-weight: 600;
			}
			.role-selection
			{
				display: flex;
				gap: 10px;
				margin-bottom: 16px;
			}
			.role-selection input[type="radio"]
			{
				display: none;
			}
			.role-btn
			{
				flex: 1;
				padding: 12px;
				border: 2px solid #e2e8f0;
				border-radius: 10px;
				cursor: pointer;
				font-size: 14px;
				font-weight: 600;
				color: #64748b;
				transition: all 0.2s;
				text-align: center;
			}
			.role-selection input[type="radio"]:checked+.role-btn
			{
				border-color: var(--primary);
				background-color: #f5f7ff;
				color: var(--primary);
			}
			.register-btn
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
				margin-top: 16px;
				transition: transform 0.1s, background 0.2s;
			}
			.register-btn:hover
			{
				background-color: var(--primary-hover);
			}
			.register-btn:active
			{
				transform: scale(0.98);
			}
			.login-link
			{
				margin-top: 20px;
				display: block;
				color: var(--primary);
				text-decoration: none;
				font-size: 0.9rem;
				font-weight: 500;
			}
			.login-link:hover
			{
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<div class = "register-card">
			<h2>Create Account</h2>
			<p>Join the workout community</p>
			<form action = "/users/register" method = "post">
				<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
				<input type = "text" name = "username" placeholder = "Username" class = "input-field" required>
				<span class = "role-container-label">Select Account Type</span>
				<div class = "role-selection">
					<input type = "radio" name = "role" value = "USER" id = "roleUser" checked>
					<label for = "roleUser" class= " role-btn">User</label>
					<input type = "radio" name = "role" value = "ADMIN" id = "roleAdmin">
					<label for = "roleAdmin" class = "role-btn">Admin</label>
				</div>
				<input type="password" name = "password" placeholder="Password" class="input-field" required>
				<input type = "password" name = "confirmPassword" placeholder = "Confirm Password" class = "input-field" required>
				<button type = "submit" class = "register-btn">Get Started</button>
			</form>
			<a href = "/login" class = "login-link">Already have an account? Sign In</a>
		</div>
	</body>
	</html>