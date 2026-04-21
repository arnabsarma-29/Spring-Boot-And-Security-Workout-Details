package com.project2.workout_app.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.models.WorkoutUserDeleteModel;
import com.project2.workout_app.models.WorkoutUserModel;
import com.project2.workout_app.models.WorkoutUserPasswordUpdateModel;
import com.project2.workout_app.models.WorkoutUserUsernameUpdateModel;
import com.project2.workout_app.service.CurrentUserProvider;
import com.project2.workout_app.service.WorkoutUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping ("/users")
public class WorkoutUserController
{
	private final WorkoutUserService workoutUserService;
	private final CurrentUserProvider currentUserProvider;
	public WorkoutUserController (WorkoutUserService workoutUserService, CurrentUserProvider currentUserProvider)
	{
		this.workoutUserService = workoutUserService;
		this.currentUserProvider = currentUserProvider;
	}
	@GetMapping ("/getWorkoutUsers")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String getWorkoutUsers (Model model)
	{
		model.addAttribute ("users", workoutUserService.findAllUsers ());
		return "users";
	}
	@PostMapping ("/register")
	public String saveWorkoutUser (WorkoutUserModel workoutUserModel, RedirectAttributes redirectAttributes)
	{
		if (!workoutUserModel.getPassword ().equals (workoutUserModel.getConfirmPassword ()))
		{
			return "redirect:/register";
		}
		workoutUserService.saveUser (workoutUserModel);
		redirectAttributes.addFlashAttribute ("message", "Workout User Saved!");
		return "redirect:/login";
	}
	@GetMapping ("/updateUsername")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String updateUsername ()
	{
		return "updateWorkoutUser";
	}
	@PostMapping ("/updateUsername")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String updateUsername (WorkoutUserUsernameUpdateModel workoutUserUsernameUpdateModel, RedirectAttributes redirectAttributes)
	{
		workoutUserService.updateUsername (workoutUserUsernameUpdateModel);
		redirectAttributes.addFlashAttribute ("message", "Username Updated!");
		return "redirect:/users/getWorkoutUsers";
	}
	@GetMapping ("/updatePassword")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String updatePassword ()
	{
		return "updateWorkoutUser";
	}
	@PostMapping ("/updatePassword")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String updatePassword (WorkoutUserPasswordUpdateModel workoutUserPasswordUpdateModel, RedirectAttributes redirectAttributes)
	{
		workoutUserService.updatePassword (workoutUserPasswordUpdateModel);
		redirectAttributes.addFlashAttribute ("message", "Password Updated!");
		return "redirect:/users/getWorkoutUsers";
	}
	@GetMapping ("/deleteWorkoutUser")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String deleteWorkoutUser ()
	{
		return "deleteWorkoutUser";
	}
	@PostMapping ("/deleteWorkoutUser")
	@PreAuthorize ("hasRole ('ADMIN')")
	public String deleteWorkoutUser (WorkoutUserDeleteModel workoutUserDeleteModel, HttpSession session)
	{
		workoutUserService.deleteUser (workoutUserDeleteModel);
		WorkoutUser currentUser = currentUserProvider.getCurrentUser ();
		if (currentUser.getUsername ().equals (workoutUserDeleteModel.getUsername ()))
		{
			session.invalidate ();
			return "redirect:/login";
		}
		return "redirect:/users/getWorkoutUsers";
	}
}