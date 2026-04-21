<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
        <title>Exercises</title>
        <style>
                :root
                {
                        --primary: #6366f1; 
                        --secondary: #64748b; 
                        --dark: #1e293b; 
                        --error: #ef4444; 
                }
                body
                {
                        font-family: 'Segoe UI',
                        sans-serif;
                        padding: 40px;
                        background: #f8fafc;
                }
                .content-wrapper
                {
                        max-width: 1100px;
                        margin: 0 auto;
                }
                .btn
                {
                        padding: 10px 20px;
                        border-radius: 10px;
                        text-decoration: none;
                        font-weight: 600;
                        transition: 0.2s;
                        border: none;
                        cursor: pointer;
                        display: inline-flex;
                        align-items: center;
                }
                .btn-dashboard
                {
                        background: var(--secondary);
                        color: white;
                        margin-right: 10px;
                }
                .workout-grid
                {
                        display: grid;
                        grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                        gap: 25px;
                        margin-top: 30px;
                }
                .workout-card
                {
                        background: white;
                        padding: 25px;
                        border-radius: 15px;
                        box-shadow: 0 4px 6px rgba(0,0,0,0.05);
                        border: 1px solid #e2e8f0;
                }
                .delete-link
                {
                        color: var(--error);
                        background: none; border: none;
                        cursor: pointer;
                        font-weight: 600;
                        padding: 0;
                        margin-top: 15px;
                }
        </style>
</head>
<body>
        <div class = "content-wrapper">
                <div style = "display: flex; justify-content: space-between; align-items: center;">
                        <h2>Exercise Library</h2>
                        <div style="display: flex; align-items: center; gap: 15px;">
                                <c:if test = "${isAdmin}">
                                        <a href = "${pageContext.request.contextPath}/admin/dashboard" class = "btn btn-dashboard">Dashboard</a>
                                        
                                        <a href = "${pageContext.request.contextPath}/workout/saveWorkout" style = "color: var(--primary); text-decoration: none; font-weight: bold;">+ Add Exercise</a>
                                </c:if>
                        </div>
                </div>
                <div class = "workout-grid">
                        <c:forEach var = "workout" items = "${workouts}">
                                <div class = "workout-card">
                                        <h3 style = "margin-top: 0;">${workout.name}</h3>
                                        <c:if test = "${isAdmin}">
                                                <form action = "${pageContext.request.contextPath}/workout/deleteWorkout" method = "post">
                                                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                                                        <input type = "hidden" name = "workoutId" value = "${workout.id}"/>
                                                        <button type = "submit" class = "delete-link">Remove Exercise</button>
                                                </form>
                                        </c:if>
                                </div>
                        </c:forEach>
                </div>
        </div>
</body>
</html>