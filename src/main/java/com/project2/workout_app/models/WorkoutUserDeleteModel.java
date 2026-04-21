package com.project2.workout_app.models;
import jakarta.validation.constraints.NotBlank;
public class WorkoutUserDeleteModel
{
	@NotBlank (message = "Username cannot be blank")
	private String username;
	public String getUsername ()
	{
		return username;
	}
	public void setUsername (String username)
	{
		this.username = username;
	}
}