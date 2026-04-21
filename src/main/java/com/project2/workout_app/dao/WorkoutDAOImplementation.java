package com.project2.workout_app.dao;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.project2.workout_app.entity.Workout;
import com.project2.workout_app.exception.EntityAlreadyExists;
import com.project2.workout_app.exception.EntityNotFound;
import com.project2.workout_app.repository.WorkoutRepository;
@Repository
public class WorkoutDAOImplementation implements WorkoutDAO
{
	public final WorkoutRepository workoutRepository;
	public WorkoutDAOImplementation (WorkoutRepository workoutRepository)
	{
		this.workoutRepository = workoutRepository;
	}
	@Override
	public Workout findById (Integer id)
	{
		return workoutRepository.findById (id).orElseThrow (() -> new EntityNotFound ("Workout not found with id " + id));
	}
	@Override
	public Workout findByName (String name)
	{
		return workoutRepository.findByName (name).orElse (null);
	}
	@Override
	public Set <Workout> findAll ()
	{
		return new HashSet <Workout> (workoutRepository.findAll ());
	}
	@Override
	public void saveWorkout (Workout workout)
	{
		if (workoutRepository.existsByName (workout.getName ()))
		{
			throw new EntityAlreadyExists ("Workout with name '" + workout.getName () + "' already exists");
		}
		workoutRepository.save (workout);
	}
	@Override
	public void deleteWorkout (Integer id)
	{
		findById (id);
		workoutRepository.deleteById (id);
	}
}