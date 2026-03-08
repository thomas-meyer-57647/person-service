package de.innologic.personservice.web.error;

import jakarta.validation.ConstraintViolationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ProblemDetailFactory problemDetailFactory;

    public GlobalExceptionHandler(ProblemDetailFactory problemDetailFactory) {
        this.problemDetailFactory = problemDetailFactory;
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException ex, HttpServletRequest request) {
        return problem(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), "NOT_FOUND", request, null);
    }

    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflict(ConflictException ex, HttpServletRequest request) {
        return problem(HttpStatus.CONFLICT, "Conflict", ex.getMessage(), "CONFLICT", request, null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        return problem(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), "BAD_REQUEST", request, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String details = ex.getBindingResult().getFieldErrors().stream()
                .map(this::toFieldErrorMessage)
                .collect(Collectors.joining("; "));
        List<Map<String, Object>> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(this::toViolationMap)
                .collect(Collectors.toList());
        return problem(HttpStatus.BAD_REQUEST, "Validation Failed", details, "VALIDATION_ERROR", request, violations);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        List<Map<String, Object>> violations = ex.getConstraintViolations().stream()
                .map(constraint -> {
                    Map<String, Object> violation = new LinkedHashMap<>();
                    violation.put("path", constraint.getPropertyPath().toString());
                    violation.put("message", constraint.getMessage());
                    violation.put("invalidValue", constraint.getInvalidValue());
                    return violation;
                })
                .collect(Collectors.toList());
        return problem(HttpStatus.BAD_REQUEST, "Validation Failed", ex.getMessage(), "VALIDATION_ERROR", request, violations);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String detail = "Invalid request parameter: " + ex.getName();
        return problem(HttpStatus.BAD_REQUEST, "Bad Request", detail, "INVALID_PARAMETER", request, null);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String detail = "Missing request parameter: " + ex.getParameterName();
        return problem(HttpStatus.BAD_REQUEST, "Bad Request", detail, "BAD_REQUEST", request, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        return problem(
                HttpStatus.CONFLICT,
                "Conflict",
                "Request violates a data constraint.",
                "CONFLICT",
                request,
                null
        );
    }

    private ProblemDetail problem(HttpStatus status, String title, String detail, String errorCode, HttpServletRequest request, List<Map<String, Object>> violations) {
        return problemDetailFactory.createProblem(status, title, detail, errorCode, request, violations);
    }

    private String toFieldErrorMessage(FieldError error) {
        return error.getField() + ": " + error.getDefaultMessage();
    }

    private Map<String, Object> toViolationMap(FieldError error) {
        Map<String, Object> violation = new LinkedHashMap<>();
        violation.put("field", error.getField());
        violation.put("message", error.getDefaultMessage());
        if (error.getRejectedValue() != null) {
            violation.put("rejectedValue", error.getRejectedValue());
        }
        return violation;
    }
}
