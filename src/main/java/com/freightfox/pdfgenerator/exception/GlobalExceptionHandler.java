package com.freightfox.pdfgenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        logger.error("Exception: ", ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");  // your custom error page
        modelAndView.addObject("message", "An error occurred. Please try again later.");
        return modelAndView;
    }

    // Handle 500 internal server errors specifically
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // Ensure this handles 500 errors
    public ModelAndView handleServerError(RuntimeException ex) {
        logger.error("Server Error: ", ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/500");  // your custom 500 page
        modelAndView.addObject("message", "Internal Server Error. Please contact support.");
        return modelAndView;
    }
}
