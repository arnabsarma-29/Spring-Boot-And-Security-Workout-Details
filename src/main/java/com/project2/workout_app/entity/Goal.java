package com.project2.workout_app.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Goal
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank (message = "Name cannot be blank")
	private String name;
	@ManyToOne
	private WorkoutUser workoutUser;
	@ManyToOne
	private Workout workout;
	public Goal ()
	{

	}
	public Goal (String name, WorkoutUser workoutUser, Workout workout)
	{
		this.name = name;
		this.workoutUser = workoutUser;
		this.workout = workout;
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
	public WorkoutUser getWorkoutUser ()
	{
		return workoutUser;
	}
	public void setWorkoutUser (WorkoutUser workoutUser)
	{
		this.workoutUser = workoutUser;
	}
	public Workout getWorkout ()
	{
		return workout;
	}
	public void setWorkout (Workout workout)
	{
		this.workout = workout;
	}
	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Goal)) return false;
		Goal goal = (Goal) o;
		return id != null && id.equals (goal.getId ());
	}
	@Override
	public int hashCode ()
	{
		return 31;
	}
}