package com.project2.workout_app.models;
import jakarta.validation.constraints.NotBlank;
public class WorkoutUserPasswordUpdateModel
{
	@NotBlank (message = "Old password cannot be blank")
	private String oldPassword;
	@NotBlank (message = "New password cannot be blank")
	private String newPassword;
	public String getOldPassword ()
	{
		return oldPassword;
	}
	public void setOldPassword (String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	public String getNewPassword ()
	{
		return newPassword;
	}
	public void setNewPassword (String newPassword)
	{
		this.newPassword = newPassword;
	}
}