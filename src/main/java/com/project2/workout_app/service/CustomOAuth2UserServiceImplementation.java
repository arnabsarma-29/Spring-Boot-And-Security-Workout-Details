package com.project2.workout_app.service;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.project2.workout_app.dao.WorkoutUserDAO;
import com.project2.workout_app.entity.WorkoutUser;
@Service
public class CustomOAuth2UserServiceImplementation extends DefaultOAuth2UserService
{
	private final WorkoutUserDAO workoutUserDAO;
	private final PasswordEncoder passwordEncoder;
	public CustomOAuth2UserServiceImplementation (WorkoutUserDAO workoutUserDAO, PasswordEncoder passwordEncoder)
	{
		this.workoutUserDAO = workoutUserDAO;
		this.passwordEncoder = passwordEncoder;
	}
	@Override
	public OAuth2User loadUser (OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
	{
		OAuth2User oAuth2User = super.loadUser (userRequest);
		String username = oAuth2User.getAttribute ("login");
		if (username == null || username.isBlank ())
		{
			throw new OAuth2AuthenticationException ("GitHub username not found");
		}
		WorkoutUser user = workoutUserDAO.findByUsername (username);
		if (user == null)
		{
			user = new WorkoutUser ();
			user.setUsername (username);
			user.setPassword (passwordEncoder.encode (UUID.randomUUID ().toString ()));
			user.setOauthOnly (true);
			user.setRole ("USER");
			workoutUserDAO.saveWorkoutUser (user);
		}
		return new org.springframework.security.oauth2.core.user.DefaultOAuth2User (java.util.Collections.singleton (new org.springframework.security.core.authority.SimpleGrantedAuthority ("ROLE_" + user.getRole ())), oAuth2User.getAttributes (), "login");
	}
}