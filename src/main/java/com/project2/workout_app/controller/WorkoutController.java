package com.project2.workout_app.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project2.workout_app.models.WorkoutSaveModel;
import com.project2.workout_app.service.CurrentUserProvider;
import com.project2.workout_app.service.GoalService;
import com.project2.workout_app.service.WorkoutService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping ("/workout")
public class WorkoutController
{
	private final WorkoutService workoutService;
	private final CurrentUserProvider currentUserProvider;
	private final GoalService goalService;
	public WorkoutController (WorkoutService workoutService, CurrentUserProvider currentUserProvider, GoalService goalService)
	{
		this.workoutService = workoutService;
		this.currentUserProvider = currentUserProvider;
		this.goalService = goalService;
	}
	@GetMapping ("/getWorkouts")
	@PreAuthorize ("hasAnyRole ('ADMIN', 'USER')")
	public String getWorkouts (Model model, Authentication auth)
	{
		boolean isAdmin = auth.getAuthorities ().stream ().anyMatch (a -> a.getAuthority ().equals ("ROLE_ADMIN"));
		if (isAdmin)
		{
			model.addAttribute ("workouts", workoutService.findAllWorkouts ());
		}
		else
		{
			model.addAttribute ("workouts", workoutService.findWorkouts (currentUserProvider.getCurrentUser ().getUsername ()));
		}
		model.addAttribute ("isAdmin", isAdmin);
		return "workout";
	}
	@GetMapping ("/saveWorkout")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String saveWorkout (Model model)
	{
		model.addAttribute("goals", goalService.findAllGoals());
		return "saveWorkout";
	}
	@PostMapping ("/saveWorkout")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String saveWorkout (WorkoutSaveModel workoutSaveModel, RedirectAttributes redirectAttributes)
	{
		workoutService.saveWorkout (workoutSaveModel);
		redirectAttributes.addFlashAttribute ("message", "Workout Saved!");
		return "redirect:/workout/getWorkouts";
	}
	@GetMapping ("/deleteWorkout")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String deleteWorkout ()
	{
		return "deleteWorkout";
	}
	@PostMapping ("/deleteWorkout")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String deleteWorkout (Integer workoutId, RedirectAttributes redirectAttributes)
	{
		workoutService.deleteWorkout (workoutId);
		redirectAttributes.addFlashAttribute ("message", "Workout Deleted!");
		return "redirect:/workout/getWorkouts";
	}
}