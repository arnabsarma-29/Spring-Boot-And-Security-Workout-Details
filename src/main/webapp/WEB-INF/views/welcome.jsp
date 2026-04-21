<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
	<!DOCTYPE html>
	<html lang = "en">
	<head>
		<meta charset = "UTF-8">
		<meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
		<title>Welcome</title>
		<style>
			:root
			{
				--primary: #6366f1;
				--primary-hover: #4f46e5;
				--dark: #1e293b;
				--bg-gradient: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
			}
			body
			{
				margin: 0;
				font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
				background: var(--bg-gradient);
				display: flex;
				justify-content: center;
				align-items: center;
				height: 100vh;
				color: var(--dark);
			}
			.welcome-card
			{
				background: rgba(255, 255, 255, 0.95);
				padding: 50px 40px;
				border-radius: 20px;
				box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
				text-align: center;
				width: 100%;
				max-width: 380px;
				transition: transform 0.3s ease;
			}
			.welcome-card:hover
			{
				transform: translateY(-5px);
			}
			h1
			{
				font-size: 2rem;
				font-weight: 700;
				margin-bottom: 8px;
				letter-spacing: -0.5px;
			}
			p
			{
				color: #64748b;
				margin-bottom: 32px;
				font-size: 0.95rem;
			}
			.btn
			{
				display: block;
				width: 100%;
				padding: 14px;
				margin: 12px 0;
				border: none;
				border-radius: 12px;
				font-size: 16px;
				font-weight: 600;
				cursor: pointer;
				text-decoration: none;
				transition: all 0.2s ease;
				box-sizing: border-box;
			}
			.login-btn
			{
				background-color: var(--primary);
				color: white;
				box-shadow: 0 4px 6px -1px rgba(99, 102, 241, 0.4);
			}
			.login-btn:hover
			{
				background-color: var(--primary-hover);
				box-shadow: 0 10px 15px -3px rgba(99, 102, 241, 0.5);
			}
			.register-btn
			{
				background-color: transparent;
				color: var(--dark);
				border: 2px solid #e2e8f0;
			}
			.register-btn:hover
			{
				background-color: #f8fafc;
				border-color: #cbd5e1;
			}
			.footer-note
			{
				margin-top: 24px;
				font-size: 0.8rem;
				color: #94a3b8;
			}
		</style>
	</head>
	<body>
		<div class = "welcome-card">
			<h1>Welcome</h1>
			<p>Please select an option to continue</p>
			<a href = "/login" class = "btn login-btn">Sign In</a>
			<a href = "/register" class = "btn register-btn">Create Account</a>
		</div>
	</body>
	</html>