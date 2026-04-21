package com.project2.workout_app.dao;
import java.util.Set;
import com.project2.workout_app.entity.Workout;
public interface WorkoutDAO
{
	Workout findById (Integer id);
	Workout findByName (String name);
	Set <Workout> findAll ();
	void saveWorkout (Workout workout);
	void deleteWorkout (Integer id);
}