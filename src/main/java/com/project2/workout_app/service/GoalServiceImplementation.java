package com.project2.workout_app.service;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.project2.workout_app.dao.GoalDAO;
import com.project2.workout_app.dao.WorkoutUserDAO;
import com.project2.workout_app.dto.GoalDTO;
import com.project2.workout_app.entity.Goal;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.exception.EntityNotFound;
import com.project2.workout_app.models.GoalSaveModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
@Validated
public class GoalServiceImplementation implements GoalService
{
	private final GoalDAO goalDAO;
	private final WorkoutUserDAO workoutUserDAO;
	public GoalServiceImplementation (GoalDAO goalDAO, WorkoutUserDAO workoutUserDAO)
	{
		this.goalDAO = goalDAO;
		this.workoutUserDAO = workoutUserDAO;
	}
	@Override
	public GoalDTO findGoal (Integer goalId)
	{
		Goal goal = goalDAO.findGoal (goalId);
		return mapToDTO (goal);
	}
	@Override
	public Set <GoalDTO> findGoals (String username)
	{
		WorkoutUser user = workoutUserDAO.findByUsername (username);
		return user.getGoals ().stream ().map (this :: mapToDTO).collect (Collectors.toSet ());
	}
	@Override
	public Set <GoalDTO> findAllGoals ()
	{
		return goalDAO.findAll ().stream ().map (this :: mapToDTO).collect (Collectors.toSet ());
	}
	@Override
	@Transactional
	public void saveGoal (@Valid GoalSaveModel goalSaveModel)
	{
		WorkoutUser workoutUser = workoutUserDAO.findByUsername (goalSaveModel.getUsername ());
		if (workoutUser == null)
		{
			throw new EntityNotFound ("User not found: " + goalSaveModel.getUsername ());
		}
		Goal existingGoal = goalDAO.findByName (goalSaveModel.getName ());
		if (existingGoal == null)
		{
			throw new EntityNotFound ("Goal with name '" + goalSaveModel.getName () + "' does not exist.");
		}
		Goal userGoal = new Goal (existingGoal.getName (), workoutUser, existingGoal.getWorkout ());
		goalDAO.saveGoal(userGoal);
	}
	@Override
	@Transactional
	public void deleteGoal (Integer goalId)
	{
		goalDAO.deleteGoal (goalId);
	}
	private GoalDTO mapToDTO (Goal goal)
	{
		String name = (goal.getWorkout() != null) ? goal.getWorkout().getName() : "Unassigned";
                return new GoalDTO (goal.getId (), goal.getName (), name);
	}
}