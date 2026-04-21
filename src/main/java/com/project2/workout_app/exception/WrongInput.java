package com.project2.workout_app.exception;
public class WrongInput extends RuntimeException
{
	public WrongInput (String message)
	{
		super (message);
	}
	public WrongInput (String message, Throwable cause)
	{
		super (message, cause);
	}
}