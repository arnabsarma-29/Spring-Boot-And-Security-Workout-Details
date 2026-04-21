package com.project2.workout_app.service;
import java.util.Set;
import com.project2.workout_app.dto.WorkoutDTO;
import com.project2.workout_app.models.WorkoutSaveModel;
import jakarta.validation.Valid;
public interface WorkoutService
{
	WorkoutDTO findWorkout (Integer id);
	Set <WorkoutDTO> findWorkouts (String username);
	Set <WorkoutDTO> findAllWorkouts ();
	void saveWorkout (@Valid WorkoutSaveModel workoutSaveModel);
	void deleteWorkout (Integer id);
}