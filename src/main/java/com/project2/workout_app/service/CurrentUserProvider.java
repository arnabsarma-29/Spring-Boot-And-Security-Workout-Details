package com.project2.workout_app.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.project2.workout_app.dao.WorkoutUserDAO;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.exception.NoSessionFound;
@Service
public class CurrentUserProvider
{
	private final WorkoutUserDAO workoutUserDAO;
	public CurrentUserProvider (WorkoutUserDAO workoutUserDAO)
	{
		this.workoutUserDAO = workoutUserDAO;
	}
	public WorkoutUser getCurrentUser ()
	{
		Authentication auth = SecurityContextHolder.getContext ().getAuthentication ();
		if (auth == null || !auth.isAuthenticated ())
		{
			throw new NoSessionFound ("No authenticated user found");
		}
		return workoutUserDAO.findByUsername (auth.getName ());
	}
}