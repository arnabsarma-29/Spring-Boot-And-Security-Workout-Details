package com.project2.workout_app.dao;
import java.util.Set;
import com.project2.workout_app.entity.Goal;
public interface GoalDAO
{
	Goal findGoal (Integer id);
	Goal findByName(String name);
	Set <Goal> findAll ();
	void saveGoal (Goal goal);
	void deleteGoal (Integer id);
}