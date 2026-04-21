package com.project2.workout_app.exception;
public class NoSessionFound extends RuntimeException
{
	public NoSessionFound (String message)
	{
		super (message);
	}
	public NoSessionFound (String message, Throwable cause)
	{
		super (message, cause);
	}
}