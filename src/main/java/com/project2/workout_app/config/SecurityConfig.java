package com.project2.workout_app.config;
import com.project2.workout_app.service.CustomOAuth2UserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
	private final CustomOAuth2UserServiceImplementation customOAuth2UserService;
	public SecurityConfig (CustomOAuth2UserServiceImplementation customOAuth2UserService)
	{
		this.customOAuth2UserService = customOAuth2UserService;
	}
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
	{
		http
		.authorizeHttpRequests (auth -> auth
                // 1. CRITICAL: Allow Spring to forward requests to JSPs internally
                .dispatcherTypeMatchers (DispatcherType.FORWARD, DispatcherType.INCLUDE).permitAll ()
                
                // 2. Publicly accessible paths
                .requestMatchers ("/", "/login", "/register", "/users/register", "/css/**", "/js/**", "/WEB-INF/views/**").permitAll ()
                
                // 3. Protected paths
                .requestMatchers ("/workout/**", "/goals/**", "/users/**").authenticated ()
                
                // 4. Everything else requires login
                .anyRequest ().authenticated ())

		.formLogin (form -> form
                .loginPage ("/login")
                .loginProcessingUrl ("/perform_login")
                .successHandler ((request, response, authentication) ->
		{
			var authorities = authentication.getAuthorities ();
			if (authorities.stream ().anyMatch (a -> a.getAuthority ().equals ("ROLE_ADMIN")))
			{
				response.sendRedirect ("/admin/dashboard");
			}
			else if (authorities.stream ().anyMatch (a -> a.getAuthority ().equals ("ROLE_USER")))
			{
				response.sendRedirect ("/user/dashboard");
			}
			else
			{
				response.sendRedirect ("/"); // fallback
			}
		})
                .failureUrl ("/login?error=true")
                .permitAll ())
		
		.logout (logout -> logout
                .logoutUrl ("/logout")
                .logoutSuccessUrl ("/login?logout")
                .invalidateHttpSession (true)
                .clearAuthentication (true)
                .permitAll ())
		
		.oauth2Login (oauth2 -> oauth2
                .loginPage ("/login")
                .userInfoEndpoint (userInfo -> userInfo.userService(customOAuth2UserService))
                .defaultSuccessUrl ("/", true))
		
		// Disable CSRF if you're just testing, but keep it for production!
		// .csrf (csrf -> csrf.disable ())
		;
		return http.build ();
	}
}