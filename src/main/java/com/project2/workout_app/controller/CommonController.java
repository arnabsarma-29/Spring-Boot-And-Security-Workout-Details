package com.project2.workout_app.controller;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CommonController
{
	public String loginCheck (Authentication authentication)
	{
		if (authentication != null && authentication.isAuthenticated () && !(authentication instanceof AnonymousAuthenticationToken))
		{
			if (authentication.getAuthorities ().stream ().anyMatch (a -> a.getAuthority ().equals ("ROLE_ADMIN")))
			{
				return "redirect:/admin/dashboard";
			}
			else
			{
				return "redirect:/user/dashboard";
			}
		}
		return null;
	}
	@GetMapping ("/")
	public String welcome (Authentication authentication)
	{
		String redirect = loginCheck (authentication);
		return (redirect != null) ? redirect : "welcome";
	}
	@GetMapping ("/login")
	public String loginPage (Authentication authentication)
	{
		String redirect = loginCheck (authentication);
		return (redirect != null) ? redirect : "login";
	}
	@GetMapping ("/register")
	public String registerPage (Authentication authentication)
	{
		String redirect = loginCheck (authentication);
		return (redirect != null) ? redirect : "register";
	}
}