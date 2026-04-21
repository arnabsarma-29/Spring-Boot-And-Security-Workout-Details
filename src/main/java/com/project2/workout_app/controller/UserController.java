package com.project2.workout_app.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController
{
	@GetMapping ("/user/dashboard")
	@PreAuthorize ("hasRole ('USER')")
	public String userDashboard ()
	{
		return "userDashboard";
	}
}