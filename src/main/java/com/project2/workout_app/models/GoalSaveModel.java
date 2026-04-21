package com.project2.workout_app.models;
import jakarta.validation.constraints.NotBlank;
public class GoalSaveModel
{
	private Integer workoutId;
	@NotBlank (message = "Name cannot be blank")
	private String name;
	@NotBlank (message = "Username cannot be blank")
	private String username;
	public Integer getWorkoutId ()
	{
		return workoutId;
	}
	public void setWorkoutId (Integer workoutId)
	{
		this.workoutId = workoutId;
	}
	public String getName ()
	{
		return name;
	}
	public void setName (String name)
	{
		this.name = name;
	}
	public String getUsername ()
	{
		return username;
	}
	public void setUsername (String username)
	{
		this.username = username;
	}
}