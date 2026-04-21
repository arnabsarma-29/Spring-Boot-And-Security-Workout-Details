<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang = "en">
<head>
        <meta charset = "UTF-8">
        <title>Add Exercise</title>
        <style>
                body
		{
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
			background: #f1f5f9;
			margin: 0;
			font-family: 'Segoe UI', system-ui, sans-serif;
                }
                .card
		{
			background: white;
			padding: 40px;
			border-radius: 20px;
			width: 380px;
			box-shadow: 0 10px 25px rgba(0,0,0,0.1);
                }
                label
		{
			font-weight: 600;
			color: #1e293b;
			display: block;
			margin-bottom: 5px;
                }
                input
		{
			width: 100%;
			padding: 12px;
			margin-bottom: 20px;
			border: 1px solid #cbd5e1;
			border-radius: 10px;
			box-sizing: border-box;
                }
                button
		{
			width: 100%;
			padding: 12px;
			background: #6366f1;
			color: white;
			border: none;
			border-radius: 10px;
			font-weight: 600;
			cursor: pointer;
			transition: background 0.2s;
                }
                button:hover
		{
			background: #4f46e5;
                }
                .back-link
		{
			display: block;
			text-align: center;
			margin-top: 15px;
			color: #64748b;
			text-decoration: none;
			font-size: 0.9rem;
                }
        </style>
</head>
<body>
        <div class = "card">
                <h2 style = "margin-top: 0; color: #1e293b;">New Exercise</h2>
                <form action = "${pageContext.request.contextPath}/workout/saveWorkout" method = "post">
                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                        
                        <label for = "exerciseName">Exercise Name</label>
                        <input type = "text" id="exerciseName" name = "name" placeholder = "e.g., Bench Press" required />
                        
                        <button type = "submit">Create Exercise</button>
                </form>
                <a href = "${pageContext.request.contextPath}/admin/dashboard" class = "back-link">Cancel and Return</a>
        </div>
</body>
</html>