package com.project2.workout_app.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface WorkoutUserDetailsService extends UserDetailsService
{
	UserDetails loadUserByUsername (String username);
}