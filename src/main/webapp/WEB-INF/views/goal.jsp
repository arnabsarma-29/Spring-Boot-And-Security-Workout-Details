<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang = "en">
<head>
        <meta charset = "UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
        <title>Goal Management</title>
        <style>
                :root
                {
                        --primary: #6366f1;
                        --secondary: #64748b;
                        --dark: #1e293b;
                        --error: #ef4444;
                        --bg: #f8fafc;
                }
                body
                {
                        margin: 0;
                        font-family: 'Segoe UI', system-ui, sans-serif;
                        background: var(--bg);
                        padding: 40px;
                }
                .container
                {
                        max-width: 1000px;
                        margin: 0 auto;
                        background: white;
                        padding: 30px;
                        border-radius: 20px;
                        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
                }
                .header
                {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        margin-bottom: 30px;
                }
                .header-actions
                {
                        display: flex;
                        gap: 10px;
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
                .btn-add
                {
                        background: var(--primary);
                        color: white;
                }
                .btn-dashboard
                {
                        background: var(--secondary);
                        color: white;
                }
                .btn-delete
                {
                        background: var(--error);
                        color: white;
                        font-size: 0.85rem;
                }
                table
                {
                        width: 100%;
                        border-collapse: collapse;
                }
                th
                {
                        text-align: left;
                        padding: 15px;
                        border-bottom: 2px solid #e2e8f0;
                        color: var(--dark);
                }
                td
                {
                        padding: 15px;
                        border-bottom: 1px solid #f1f5f9;
                        vertical-align: middle;
                        line-height: 1;
                }
        </style>
</head>
<body>
        <div class = "container">
                <div class = "header">
                        <h2>Workout Goals</h2>
                        <div class = "header-actions">
                                <c:if test = "${isAdmin}">
                                        <a href = "${pageContext.request.contextPath}/admin/dashboard" class = "btn btn-dashboard">Dashboard</a>
                                        <a href = "${pageContext.request.contextPath}/goals/saveGoal" class = "btn btn-add">+ New Goal</a>
                                </c:if>
                        </div>
                </div>
                <table>
                        <thead>
                                <tr>
                                        <th>Goal Name</th>
                                        <th>Assigned Workout</th>
                                        <c:if test = "${isAdmin}"><th>Actions</th></c:if>
                                </tr>
                        </thead>
                        <tbody>
                                <c:forEach var = "goal" items = "${goals}">
                                        <tr>
                                                <td><strong>${goal.name}</strong></td>
                                                <td>${goal.workoutName}</td>
                                                <c:if test = "${isAdmin}">
                                                        <td>
                                                                <form action = "${pageContext.request.contextPath}/goals/deleteGoal" method = "post" style = "margin:0;">
                                                                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                                                                        <input type = "hidden" name = "goalId" value = "${goal.id}"/>
                                                                        <button type = "submit" class = "btn btn-delete">Delete</button>
                                                                </form>
                                                        </td>
                                                </c:if>
                                        </tr>
                                </c:forEach>
                        </tbody>
                </table>
        </div>
</body>
</html>