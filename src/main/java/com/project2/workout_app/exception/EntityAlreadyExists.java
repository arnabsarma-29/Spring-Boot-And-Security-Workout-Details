package com.project2.workout_app.exception;
public class EntityAlreadyExists extends RuntimeException
{
	public EntityAlreadyExists (String message)
	{
		super (message);
	}
	public EntityAlreadyExists (String message, Throwable cause)
	{
		super (message, cause);
	}
}