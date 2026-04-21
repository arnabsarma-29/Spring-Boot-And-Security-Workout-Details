package com.project2.workout_app.service;
import java.util.Set;
import com.project2.workout_app.dto.GoalDTO;
import com.project2.workout_app.models.GoalSaveModel;
import jakarta.validation.Valid;
public interface GoalService
{
	GoalDTO findGoal (Integer goalId);
	Set <GoalDTO> findGoals (String username);
	Set <GoalDTO> findAllGoals ();
	void saveGoal (@Valid GoalSaveModel goalSaveModel);
	void deleteGoal (Integer goalId);
}