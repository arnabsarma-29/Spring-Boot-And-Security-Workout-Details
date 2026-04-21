package com.project2.workout_app.dao;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.project2.workout_app.entity.Goal;
import com.project2.workout_app.exception.EntityAlreadyExists;
import com.project2.workout_app.exception.EntityNotFound;
import com.project2.workout_app.repository.GoalRepository;
@Repository
public class GoalDAOImplementation implements GoalDAO
{
	public final GoalRepository goalRepository;
	public GoalDAOImplementation (GoalRepository goalRepository)
	{
		this.goalRepository = goalRepository;
	}
	@Override
	public Goal findGoal (Integer id)
	{
		return goalRepository.findById (id).orElseThrow (() -> new EntityNotFound ("Goal not found"));
	}
	@Override
	public Goal findByName(String name)
	{
		return goalRepository.findByName (name).orElse (null);
	}
	@Override
	public Set <Goal> findAll ()
	{
		return goalRepository.findAll ().stream ().collect (Collectors.toSet ());
	}
	@Override
	public void saveGoal (Goal goal)
	{
		if (goalRepository.existsByNameAndWorkoutUser (goal.getName (), goal.getWorkoutUser ()))
		{
			throw new EntityAlreadyExists ("This user already has a goal named '" + goal.getName () + "'");
		}
    goalRepository.saveAndFlush(goal);
	}
	@Override
	public void deleteGoal (Integer goalId)
	{
		findGoal (goalId);
		goalRepository.deleteById (goalId);
	}
}