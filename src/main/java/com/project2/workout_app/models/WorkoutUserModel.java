package com.project2.workout_app.models;
import jakarta.validation.constraints.NotBlank;
public class WorkoutUserModel
{
	@NotBlank (message = "Username cannot be blank")
	private String username;
	@NotBlank (message = "Password cannot be blank")
	private String password;
	@NotBlank (message = "Confirm Password cannot be blank")
	private String confirmPassword;
	@NotBlank (message = "Role cannot be blank")
	private String role;
	public WorkoutUserModel ()
	{

	}
	public String getUsername ()
	{
		return username;
	}
	public void setUsername (String username)
	{
		this.username = username;
	}
	public String getPassword ()
	{
		return password;
	}
	public void setPassword (String password)
	{
		this.password = password;
	}
	public String getConfirmPassword ()
	{
		return confirmPassword;
	}
	public void setConfirmPassword (String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}
	public String getRole ()
	{
		return role;
	}
	public void setRole (String role)
	{
		this.role = role;
	}
}