package com.project2.workout_app.dao;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.exception.EntityNotFound;
import com.project2.workout_app.exception.EntityAlreadyExists;
import com.project2.workout_app.repository.WorkoutUserRepository;
@Repository
public class WorkoutUserDAOImplementation implements WorkoutUserDAO
{
	private final WorkoutUserRepository workoutUserRepository;
	public WorkoutUserDAOImplementation (WorkoutUserRepository workoutUserRepository)
	{
		this.workoutUserRepository = workoutUserRepository;
	}
	@Override
	public WorkoutUser findById (Integer id)
	{
		return workoutUserRepository.findById (id).orElseThrow (() -> new EntityNotFound ("User not found"));
	}
	@Override
	public WorkoutUser findByUsername (String username)
	{
		return workoutUserRepository.findByUsername (username);
	}
	@Override
	public Set <WorkoutUser> findAll ()
	{
		return new HashSet <WorkoutUser> (workoutUserRepository.findAll ());
	}
	@Override
	public void saveWorkoutUser (WorkoutUser workoutUser)
	{
		if (workoutUserRepository.existsByUsername (workoutUser.getUsername ()))
		{
			throw new EntityAlreadyExists ("User with name '" + workoutUser.getUsername () + "' already exists");
		}
		workoutUserRepository.save (workoutUser);
	}
	@Override
	public void updateUsername (String oldUsername, String newUsername)
	{
		if (!workoutUserRepository.existsByUsername (oldUsername))
		{
			throw new EntityNotFound ("User not found");
		}
		workoutUserRepository.updateUsername (oldUsername, newUsername);
	}
	@Override
	public void deleteWorkoutUser (WorkoutUser workoutUser)
	{
		findById (workoutUser.getId ());
		workoutUserRepository.delete (workoutUser);
	}
}