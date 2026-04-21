package com.project2.workout_app.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.project2.workout_app.dao.WorkoutUserDAO;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.exception.EntityNotFound;
@Service
public class WorkoutUserDetailsServiceImplementation implements WorkoutUserDetailsService
{
	private final WorkoutUserDAO workoutUserDAO;
	public WorkoutUserDetailsServiceImplementation (WorkoutUserDAO workoutUserDAO)
	{
		this.workoutUserDAO = workoutUserDAO;
	}
	@Override
	public UserDetails loadUserByUsername (String username)
	{
		WorkoutUser user = workoutUserDAO.findByUsername (username);
		if (user == null)
		{
			throw new EntityNotFound ("User not found!");
		}
		return org.springframework.security.core.userdetails.User.withUsername (user.getUsername ()).password (user.getPassword ()).roles (user.getRole ()).build ();
	}
}