<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang = "en">
<head>
        <meta charset = "UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
        <title>User Dashboard</title>
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
                        min-height: 100vh;
                }
                .navbar
                {
                        background: white;
                        padding: 15px 40px;
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
                }
                .navbar h2
                {
                        margin: 0;
                        color: var(--dark);
                        font-size: 1.5rem;
                }
                .logout-btn
                {
                        padding: 10px 20px;
                        background-color: var(--error);
                        color: white;
                        border: none;
                        border-radius: 10px;
                        font-weight: 600;
                        cursor: pointer;
                        transition: opacity 0.2s;
                }
                .logout-btn:hover
                {
                        opacity: 0.9;
                }
                .container
                {
                        display: flex;
                        justify-content: center;
                        gap: 25px;
                        max-width: 600px;
                        margin: 100px auto;
                        padding: 0 20px;
                }
                .card
                {
                        background: white;
                        flex: 1;
                        height: 140px;
                        display: flex;
                        flex-direction: column;
                        justify-content: center;
                        align-items: center;
                        border-radius: 20px;
                        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
                        text-decoration: none;
                        color: var(--dark);
                        font-weight: 600;
                        font-size: 1.15rem;
                        transition: all 0.2s;
                }
                .card:hover
                {
                        transform: translateY(-5px);
                        box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1);
                        color: var(--primary);
                }
        </style>
</head>
<body>
        <div class = "navbar">
                <h2>My Dashboard</h2>
                <form action = "${pageContext.request.contextPath}/logout" method = "post">
                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                        <button type = "submit" class = "logout-btn">Logout</button>
                </form>
        </div>
        <div class = "container">
                <a href = "${pageContext.request.contextPath}/workout/getWorkouts" class = "card">
                        <span>Workout</span>
                </a>
                <a href = "${pageContext.request.contextPath}/goals/getGoals" class = "card">
                        <span>Goals</span>
                </a>
        </div>
</body>
</html>