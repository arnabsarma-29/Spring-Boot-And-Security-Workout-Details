package com.project2.workout_app.dto;
import jakarta.validation.constraints.NotBlank;
public class WorkoutDTO
{
	private Integer id;
	@NotBlank (message = "Workout name cannot be blank")
	private String name;
	public WorkoutDTO (Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}
	public Integer getId ()
	{
		return id;
	}
	public void setId (Integer id)
	{
		this.id = id;
	}
	public String getName ()
	{
		return name;
	}
	public void setName (String name)
	{
		this.name = name;
	}
}