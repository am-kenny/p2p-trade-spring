package com.mycompany.p2pTradeSpringProject.exception;

import com.mycompany.p2pTradeSpringProject.components.Debug;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Debug debug;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(Debug debug) {
        this.debug = debug;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleTradeNotFoundException(EntityNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorCode", "404");

        if (debug.isDebugMode()) {
            logger.debug(e.getMessage(), e);
            model.addAttribute("stackTrace", getStackTraceAsString(e));
        }

        return "error";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorCode", "404");

        if (debug.isDebugMode()) {
            logger.debug(e.getMessage(), e);
            model.addAttribute("stackTrace", getStackTraceAsString(e));
        }

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception e, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        model.addAttribute("errorCode", "500");

        if (debug.isDebugMode()) {
            logger.error(e.getMessage(), e);
            model.addAttribute("stackTrace", getStackTraceAsString(e));
        }

        return "error";
    }

    private String getStackTraceAsString(Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            stackTrace.append(element.toString()).append("\n");
        }
        return stackTrace.toString();
    }

}
