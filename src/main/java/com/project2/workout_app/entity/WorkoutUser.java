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
public class WorkoutUser
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank (message = "Username cannot be blank")
	private String username;
	@NotBlank (message = "Password cannot be blank")
	private String password;
	@NotBlank (message = "Role cannot be blank")
	private String role;
	private boolean oauthOnly;
	@OneToMany (mappedBy = "workoutUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set <Goal> goals = new HashSet <> ();
	public WorkoutUser ()
	{

	}
	public WorkoutUser (String username, String password, String role, Set <Goal> goals)
	{
		this.username = username;
		this.password = password;
		this.role = role;
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
	public String getRole ()
	{
		return role;
	}
	public void setRole (String role)
	{
		this.role = role;
	}
	public Set<Goal> getGoals ()
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
		goal.setWorkoutUser( this);
	}
	public void removeGoal (Goal goal)
	{
		goals.remove (goal);
		goal.setWorkoutUser (null);
	}
	public boolean isOauthOnly ()
	{
		return oauthOnly;
	}
	public void setOauthOnly (boolean oauthOnly)
	{
		this.oauthOnly = oauthOnly;
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