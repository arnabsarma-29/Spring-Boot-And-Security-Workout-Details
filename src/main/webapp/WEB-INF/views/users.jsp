<%@ page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang = "en">
<head>
        <meta charset = "UTF-8">
        <meta name = "viewport" content = "width=device-width, initial-scale = 1.0">
        <title>Manage Users</title>
        <style>
                :root
                {
                        --primary: #6366f1;
                        --primary-hover: #4f46e5;
                        --dark: #1e293b;
                        --error: #ef4444;
                        --success: #10b981;
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
                .btn-dash
                {
                        padding: 10px 20px;
                        background-color: var(--dark);
                        color: white;
                        border: none;
                        border-radius: 10px;
                        font-weight: 600;
                        text-decoration: none;
                        transition: opacity 0.2s;
                }
                .btn-dash:hover
                {
                        opacity: 0.9;
                }
                .container
                {
                        max-width: 900px;
                        margin: 60px auto;
                        padding: 0 20px;
                }
                .user-card
                {
                        background: white;
                        border-radius: 20px;
                        padding: 25px;
                        margin-bottom: 20px;
                        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
                }
                .user-info
                {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        margin-bottom: 20px;
                        color: var(--dark);
                        font-size: 1.1rem;
                        font-weight: 600;
                }
                .role-badge
                {
                        background: #e2e8f0;
                        padding: 4px 10px;
                        border-radius: 8px;
                        font-size: 0.8rem;
                        font-weight: 600;
                }
                .actions
                {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                        gap: 15px;
                }
                form
                {
                        background: #f8fafc;
                        padding: 12px;
                        border-radius: 12px;
                        border: 1px solid #edf2f7;
                }
                label
                {
                        display: block;
                        font-size: 0.8rem;
                        font-weight: 600;
                        color: #64748b;
                        margin-bottom: 5px;
                }
                input, select
                {
                        padding: 8px;
                        border: 1px solid #cbd5e1;
                        border-radius: 8px;
                        width: calc(100% - 18px);
                        margin-bottom: 8px;
                }
                button
                {
                        padding: 10px 15px;
                        cursor: pointer;
                        border-radius: 8px;
                        border: none;
                        font-weight: 600;
                        transition: 0.2s;
                        width: 100%;
                }
                .btn-update
                {
                        background: var(--primary);
                        color: white;
                }

                .btn-update:hover
                {
                        background: var(--primary-hover);
                }
                .btn-delete
                {
                        background: var(--error);
                        color: white;
                }
                .btn-success
                {
                        background: var(--success);
                        color: white;
                }
                .msg
                {
                        color: var(--success);
                        font-weight: 600;
                        margin-bottom: 20px;
                }
        </style>
</head>
<body>
        <div class = "navbar">
                <h2>User Management</h2>
                <a href = "${pageContext.request.contextPath}/admin/dashboard" class = "btn-dash">
                        Back to Dashboard
                </a>
        </div>
        <div class = "container">
                <c:if test = "${not empty message}">
                        <p class = "msg">${message}</p>
                </c:if>
                <c:forEach var = "user" items = "${users}">
                        <div class = "user-card">

                                <div class = "user-info">
                                        <span>
                                                ${user.username}
                                        </span>
                                        <span class = "role-badge">
                                                ${user.role}
                                        </span>
                                </div>
                                <div class = "actions">
                                        <form action = "${pageContext.request.contextPath}/users/updateUsername" method = "post">
                                                <label>Change Username</label>
                                                <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                                                <input type = "hidden" name = "oldUsername" value = "${user.username}" />
                                                <input type = "text" name = "username" placeholder = "New username" required />
                                                <button type = "submit" class = "btn-update">
                                                        Update Name
                                                </button>
                                        </form>
                                        <form action = "${pageContext.request.contextPath}/users/updatePassword" method = "post">
                                                <label>Reset Password</label>
                                                <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                                                <input type = "hidden" name = "username" value = "${user.username}" />
                                                <input type = "password" name = "oldPassword" placeholder = "Old password" required />
                                                <input type = "password" name = "newPassword" placeholder = "New password" required />
                                                <button type = "submit" class = "btn-update">
                                                        Update Password
                                                </button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/goals/saveGoal" method="post">
                                                        <label>Assign Goal (Enter Workout Name)</label>
                                                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                                                        <input type = "hidden" name = "username" value = "${user.username}" />
                                                        <input type =  "text" name = "name" placeholder = "Enter Workout Name (e.g. Legs)" required />
                                                        <button type = "submit" class = "btn-success">Assign Goal</button>
                                        </form>
                                        <form action = "${pageContext.request.contextPath}/users/deleteWorkoutUser" method = "post">
                                                <label>Danger Zone</label>
                                                <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                                                <input type = "hidden" name = "username" value = "${user.username}" />
                                                <p style = "font-size: 0.75rem; color: var(--error); margin: 0 0 8px 0;">
                                                        This action is permanent.
                                                </p>
                                                <button type = "submit" class = "btn-delete"
                                                        onclick = "return confirm ('Delete ${user.username}?')">
                                                        Delete User
                                                </button>
                                        </form>
                                </div>
                        </div>
                </c:forEach>
        </div>
</body>
</html>