package com.project2.workout_app.service;
import java.util.Set;
import com.project2.workout_app.dto.WorkoutUserDTO;
import com.project2.workout_app.models.WorkoutUserDeleteModel;
import com.project2.workout_app.models.WorkoutUserModel;
import com.project2.workout_app.models.WorkoutUserPasswordUpdateModel;
import com.project2.workout_app.models.WorkoutUserUsernameUpdateModel;
import jakarta.validation.Valid;
public interface WorkoutUserService
{
	Set <WorkoutUserDTO> findAllUsers ();
	void saveUser (@Valid WorkoutUserModel user);
	void updateUsername (@Valid WorkoutUserUsernameUpdateModel workoutUserUsernameUpdateModel);
	void updatePassword (@Valid WorkoutUserPasswordUpdateModel workoutUserPasswordUpdateModel);
	void deleteUser (@Valid WorkoutUserDeleteModel workoutUserDeleteModel);
}