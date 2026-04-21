package com.project2.workout_app.dao;
import java.util.Set;
import com.project2.workout_app.entity.WorkoutUser;
public interface WorkoutUserDAO
{
	WorkoutUser findById (Integer id);
	WorkoutUser findByUsername (String username);
	Set <WorkoutUser> findAll ();
	void saveWorkoutUser (WorkoutUser workoutUser);
	void updateUsername (String oldUsername, String newUsername);
	void deleteWorkoutUser (WorkoutUser workoutUser);
}