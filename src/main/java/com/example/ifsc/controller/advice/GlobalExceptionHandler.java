package com.example.ifsc.controller.advice;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.example.ifsc.domain.ApiError;
import com.example.ifsc.exception.BranchNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sumit.bhardwaj
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    

    /**
     * Provides handling for exceptions throughout this service.
     *
     * @param ex The target exception
     * @param request The current request
     */
    @ExceptionHandler({
            BranchNotFoundException.class,
            DataAccessException.class
    })
    @Nullable
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        log.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

        if (ex instanceof BranchNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            BranchNotFoundException unfe = (BranchNotFoundException) ex;

            return handleBranchNotFoundException(unfe, headers, status, request);
		} /*
			 * else if (ex instanceof DataAccessException) { HttpStatus status =
			 * HttpStatus.INTERNAL_SERVER_ERROR; DataAccessException unfe =
			 * (DataAccessException) ex;
			 * 
			 * return handleDataAccessException(unfe, headers, status, request); }
			 */
        
        else {
            if (log.isWarnEnabled()) {
                log.warn("Unknown exception type: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /**
     * Customize the response for DataAccessException.
     *
     * @param ex The exception
     * @param headers The headers to be written to the response
     * @param status The selected response status
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<ApiError> handleDataAccessException(DataAccessException ex,
                                                                    HttpHeaders headers, HttpStatus status,
                                                                    WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }
    
    
    /**
     * Customize the response for BranchNotFoundException.
     *
     * @param ex The exception
     * @param headers The headers to be written to the response
     * @param status The selected response status
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<ApiError> handleBranchNotFoundException(BranchNotFoundException ex,
                                                                    HttpHeaders headers, HttpStatus status,
                                                                    WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }

    

    /**
     * A single place to customize the response body of all Exception types.
     *
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex The exception
     * @param body The body for the response
     * @param headers The headers for the response
     * @param status The response status
     * @param request The current request
     */
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body,
                                                                     HttpHeaders headers, HttpStatus status,
                                                                     WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}