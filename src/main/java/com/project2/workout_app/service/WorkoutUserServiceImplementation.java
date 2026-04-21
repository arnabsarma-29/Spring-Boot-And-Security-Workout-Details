package com.project2.workout_app.service;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.project2.workout_app.dao.WorkoutUserDAO;
import com.project2.workout_app.dto.WorkoutUserDTO;
import com.project2.workout_app.entity.WorkoutUser;
import com.project2.workout_app.exception.EntityNotFound;
import com.project2.workout_app.exception.WrongInput;
import com.project2.workout_app.models.WorkoutUserDeleteModel;
import com.project2.workout_app.models.WorkoutUserModel;
import com.project2.workout_app.models.WorkoutUserPasswordUpdateModel;
import com.project2.workout_app.models.WorkoutUserUsernameUpdateModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
@Validated
public class WorkoutUserServiceImplementation implements WorkoutUserService
{
	public final WorkoutUserDAO workoutUserDAO;
	private final PasswordEncoder passwordEncoder;
	private final CurrentUserProvider currentUserProvider;
	public WorkoutUserServiceImplementation (WorkoutUserDAO workoutUserDAO, PasswordEncoder passwordEncoder, CurrentUserProvider currentUserProvider)
	{
		this.workoutUserDAO = workoutUserDAO;
		this.passwordEncoder = passwordEncoder;
		this.currentUserProvider = currentUserProvider;
	}
	@Override
	public Set <WorkoutUserDTO> findAllUsers ()
	{
		return workoutUserDAO.findAll ().stream ().map (this::mapToDTO).collect (Collectors.toSet ());
	}
	@Override
	public void saveUser (@Valid WorkoutUserModel workoutUserModel)
	{
		String encodedPassword = passwordEncoder.encode (workoutUserModel.getPassword ());
		WorkoutUser workoutUser = new WorkoutUser (workoutUserModel.getUsername (), encodedPassword, workoutUserModel.getRole (), null);
		workoutUserDAO.saveWorkoutUser (workoutUser);
	}
	@Override
	@Transactional
	public void updateUsername (@Valid WorkoutUserUsernameUpdateModel workoutUserUsernameUpdateModel)
	{
		WorkoutUser workoutUser = currentUserProvider.getCurrentUser ();
		workoutUserDAO.updateUsername (workoutUser.getUsername (), workoutUserUsernameUpdateModel.getUsername ());
	}
	@Override
	@Transactional
	public void updatePassword (@Valid WorkoutUserPasswordUpdateModel workoutUserPasswordUpdateModel)
	{
		WorkoutUser workoutUser = currentUserProvider.getCurrentUser ();
		if (!passwordEncoder.matches (workoutUserPasswordUpdateModel.getOldPassword (), workoutUser.getPassword ()))
		{
			throw new WrongInput ("Wrong Password!");
		}
		workoutUser.setPassword (passwordEncoder.encode (workoutUserPasswordUpdateModel.getNewPassword ()));
		workoutUserDAO.saveWorkoutUser (workoutUser);
	}
	@Override
	@Transactional
	public void deleteUser (@Valid WorkoutUserDeleteModel workoutUserDeleteModel)
	{
		WorkoutUser workoutUser = workoutUserDAO.findByUsername (workoutUserDeleteModel.getUsername ());
		if (workoutUser == null)
		{
			throw new EntityNotFound ("User not found");
		}
		workoutUserDAO.deleteWorkoutUser (workoutUser);
	}
	private WorkoutUserDTO mapToDTO (WorkoutUser user)
	{
		return new WorkoutUserDTO (user.getId (), user.getUsername (), null, user.getRole ());
	}
}