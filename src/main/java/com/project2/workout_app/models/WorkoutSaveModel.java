package com.project2.workout_app.models;
import jakarta.validation.constraints.NotBlank;
public class WorkoutSaveModel
{
	@NotBlank (message = "Workout name cannot be blank")
	private String name;
	public String getName ()
	{
		return name;
	}
	public void setName (String name)
	{
		this.name = name;
	}
}