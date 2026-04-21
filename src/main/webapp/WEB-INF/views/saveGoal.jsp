<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
        <title>New Goal</title>
        <style>
                body
                {
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        background: #f1f5f9; margin:0;
                        font-family: sans-serif;
                }
                .card
                {
                        background: white;
                        padding: 40px;
                        border-radius: 20px;
                        width: 350px;
                        box-shadow: 0 10px 25px rgba(0,0,0,0.1);
                }
                input, select
                {
                        width: 100%;
                        padding: 12px;
                        margin: 10px 0;
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
                        border: none; border-radius: 10px;
                        font-weight: 600;
                        cursor: pointer;
                        margin-top: 10px;
                }
                label
                {
                        font-weight: 600;
                        color: #1e293b;
                        font-size: 0.9rem;
                }
                .back-link
		{
                        display: block;
                        text-align: center;
                        margin-top: 20px;
                        color: #64748b;
                        text-decoration: none;
                        font-size: 0.9rem;
                        font-weight: 500;
                }
                .back-link:hover
		{
                        color: #1e293b;
                        text-decoration: underline;
                }
        </style>
</head>
<body>
        <div class = "card">
                <h2 style = "margin-top: 0;">Create Goal</h2>
                <form action = "${pageContext.request.contextPath}/goals/saveGoal" method = "post">
                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                        <input type = "hidden" name = "username" value = "${pageContext.request.userPrincipal.name}"/>
                        <label>Goal Name</label>
                        <input type = "text" name = "name" placeholder = "e.g., Build Muscle" required />
                        <label>Select Workout</label>
                        <select name = "workoutId" required>
                                <option value = "">-- Choose a Workout --</option>
                                <c:forEach var = "workout" items = "${workouts}">
                                        <option value = "${workout.id}">${workout.name}</option>
                                </c:forEach>
                        </select>
                        <button type = "submit">Save Goal</button>
                </form>
                <a href = "${pageContext.request.contextPath}/goals/getGoals" class = "back-link">Cancel and Return</a>
        </div>
</body>
</html>