package com.project2.workout_app.dto;
import jakarta.validation.constraints.NotBlank;
public class GoalDTO
{
	private Integer id;
	@NotBlank (message = "Goal name cannot be blank")
	private String name;
	private String workoutName;
	public GoalDTO (Integer id, String name, String workoutName)
	{
		this.id = id;
		this.name = name;
		this.workoutName = workoutName;
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
	public String getWorkoutName ()
	{
		return workoutName;
	}
	public void setWorkoutName (String workoutName)
	{
		this.workoutName = workoutName;
	}
}