package com.project2.workout_app.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project2.workout_app.exception.EntityNotFound;
import com.project2.workout_app.models.GoalSaveModel;
import com.project2.workout_app.service.CurrentUserProvider;
import com.project2.workout_app.service.GoalService;
import com.project2.workout_app.service.WorkoutService;
import com.project2.workout_app.service.WorkoutUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping ("/goals")
public class GoalController
{
	private final GoalService goalService;
	private final WorkoutUserService workoutUserService;
	private final CurrentUserProvider currentUserProvider;
	private final WorkoutService workoutService;
	public GoalController (GoalService goalService, WorkoutUserService workoutUserService, CurrentUserProvider currentUserProvider, WorkoutService workoutService)
	{
		this.goalService = goalService;
		this.workoutUserService = workoutUserService;
		this.currentUserProvider = currentUserProvider;
		this.workoutService = workoutService;
	}
	@GetMapping ("/getGoals")
	@PreAuthorize ("hasAnyRole ('ADMIN', 'USER')")
	public String getGoals (Model model, Authentication auth)
	{
		boolean isAdmin = auth.getAuthorities ().stream ().anyMatch (a -> a.getAuthority ().equals ("ROLE_ADMIN"));
		if (isAdmin)
		{
			model.addAttribute ("goals", goalService.findAllGoals ());
		}
		else
		{
			model.addAttribute ("goals", goalService.findGoals (currentUserProvider.getCurrentUser ().getUsername ()));
		}
		model.addAttribute ("isAdmin", isAdmin);
		return "goal";
	}
	@GetMapping ("/saveGoal")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String saveGoal (Model model)
	{
		model.addAttribute ("users", workoutUserService.findAllUsers ());
		model.addAttribute ("workouts", workoutService.findAllWorkouts ());
		return "saveGoal";
	}
	@PostMapping ("/saveGoal")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String saveGoal (GoalSaveModel goalSaveModel, RedirectAttributes redirectAttributes)
	{
		try
		{
			goalService.saveGoal (goalSaveModel);
			redirectAttributes.addFlashAttribute ("message", "Goal Saved!");
		}
		catch (EntityNotFound e)
		{
			redirectAttributes.addFlashAttribute ("message", "Error: " + e.getMessage ());
			return "redirect:/users/getWorkoutUsers";
		}
		catch (Exception e)
		{
			redirectAttributes.addFlashAttribute ("message", "An unexpected error occurred.");
		}
		return "redirect:/goals/getGoals";
	}
	@GetMapping ("/deleteGoal")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String deleteGoal ()
	{
		return "deleteGoal";
	}
	@PostMapping ("/deleteGoal")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String deleteGoal (Integer goalId, RedirectAttributes redirectAttributes)
	{
		goalService.deleteGoal (goalId);
		redirectAttributes.addFlashAttribute ("message", "Goal Deleted!");
		return "redirect:/goals/getGoals";
	}
}