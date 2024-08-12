package com.mycompany.p2pTradeSpringProject.exception;

import com.mycompany.p2pTradeSpringProject.component.Debug;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_TEMPLATE = "common/error";

    private final Debug debug;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(Debug debug) {
        this.debug = debug;
    }

    @ExceptionHandler({EntityNotFoundException.class, NoResourceFoundException.class})
    public ModelAndView handleTradeNotFoundException(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return getModelAndView(e, status);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        return getModelAndView(e, status);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(AccessDeniedException e) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return getModelAndView(e, status);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ModelAndView mav = getModelAndView(e, status);

        if (!debug.isDebugMode()) {
            mav.addObject("errorMessage", "An unexpected error occurred");
        }
        return mav;
    }

    private ModelAndView getModelAndView(Exception e, HttpStatus status) {
        ModelAndView mav = new ModelAndView();

        mav.setStatus(status);
        mav.setViewName(ERROR_TEMPLATE);
        mav.addObject("errorMessage", e.getMessage());
        mav.addObject("errorCode", status.toString());

        if (debug.isDebugMode()) {
            logger.debug(e.getMessage(), e);
            mav.addObject("exception", e);
            mav.addObject("stackTrace", getStackTraceAsString(e));
        }

        return mav;
    }

    private String getStackTraceAsString(Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            stackTrace.append(element.toString()).append("\n");
        }
        return stackTrace.toString();
    }

}
