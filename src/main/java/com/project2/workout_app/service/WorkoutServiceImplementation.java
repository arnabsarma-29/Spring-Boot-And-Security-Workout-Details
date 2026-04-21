package com.project2.workout_app.service;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.project2.workout_app.dao.GoalDAO;
import com.project2.workout_app.dao.WorkoutDAO;
import com.project2.workout_app.dao.WorkoutUserDAO;
import com.project2.workout_app.dto.WorkoutDTO;
import com.project2.workout_app.entity.Goal;
import com.project2.workout_app.entity.Workout;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.models.WorkoutSaveModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
@Validated
public class WorkoutServiceImplementation implements WorkoutService
{
	public final WorkoutDAO workoutDAO;
	public final GoalDAO goalDAO;
	private final WorkoutUserDAO workoutUserDAO;
	public WorkoutServiceImplementation (WorkoutDAO workoutDAO, GoalDAO goalDAO, WorkoutUserDAO workoutUserDAO)
	{
		this.workoutDAO = workoutDAO;
		this.goalDAO = goalDAO;
		this.workoutUserDAO = workoutUserDAO;
	}
	@Override
	public WorkoutDTO findWorkout (Integer id)
	{
		Workout workout = workoutDAO.findById (id);
		return mapToDTO (workout);
	}
	@Override
	public Set <WorkoutDTO> findWorkouts (String username)
	{
		WorkoutUser user = workoutUserDAO.findByUsername (username);
		return user.getGoals ().stream ().map (Goal :: getWorkout).filter (w -> w != null).collect (Collectors.toSet ()).stream ().map (this :: mapToDTO).collect (Collectors.toSet ());
	}
	@Override
	public Set <WorkoutDTO> findAllWorkouts ()
	{
		return workoutDAO.findAll ().stream ().map (this :: mapToDTO).collect (Collectors.toSet ());
	}
	@Override
	public void saveWorkout (@Valid WorkoutSaveModel workoutSaveModel)
	{
		Workout workout = new Workout (workoutSaveModel.getName (), null);
		workoutDAO.saveWorkout (workout);
	}
	@Override
	@Transactional
	public void deleteWorkout (Integer id)
	{
		workoutDAO.deleteWorkout (id);
	}
	private WorkoutDTO mapToDTO (Workout workout)
	{
		return new WorkoutDTO (workout.getId (), workout.getName ());
	}
}