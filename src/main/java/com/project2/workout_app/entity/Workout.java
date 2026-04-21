package com.project2.workout_app.entity;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Workout
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank (message = "Workout name cannot be blank")
	private String name;
	@OneToMany (mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set <Goal> goals = new HashSet <> ();
	public Workout ()
	{

	}
	public Workout (String name, Set <Goal> goals)
	{
		this.name = name;
		this.goals = goals;
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
	public Set <Goal> getGoals ()
	{
		return goals;
	}
	public void setGoals (Set <Goal> goals)
	{
		this.goals = goals;
	}
	public void addGoal (Goal goal)
	{
		goals.add (goal);
		goal.setWorkout (this);
	}
	public void removeGoal (Goal goal)
	{
		goals.remove (goal);
		goal.setWorkout (null);
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