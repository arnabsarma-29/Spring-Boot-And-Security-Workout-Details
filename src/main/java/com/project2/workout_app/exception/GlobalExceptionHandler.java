package com.project2.workout_app.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
@ControllerAdvice
public class GlobalExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);
	@ExceptionHandler (EntityNotFound.class)
	public String handleEntityNotFound (EntityNotFound e, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute ("message", "User Not Found!");
		return "redirect:/login";
	}
	@ExceptionHandler (EntityAlreadyExists.class)
	public String handleEntityAlreadyExists (EntityAlreadyExists e, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute ("message", "User Already Exists!");
		return "redirect:/login";
	}
	@ExceptionHandler (WrongInput.class)
	public String handleWrongInput (WrongInput e, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute ("message", "Wrong Input!");
		return "redirect:/login";
	}
	@ExceptionHandler (NoSessionFound.class)
	public String handleNoSessionFound (NoSessionFound e, RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addFlashAttribute ("message", "No Session Found!");
		return "redirect:/login";
	}
	@ExceptionHandler (Exception.class)
	public String handleGeneralException (Exception e, HttpSession session, RedirectAttributes redirectAttributes)
	{
		logger.error ("Unexpected error", e);
		if (session != null)
		{
			session.invalidate();
		}
		redirectAttributes.addFlashAttribute ("message", "500 Server Error!");
		return "redirect:/login";
	}
}