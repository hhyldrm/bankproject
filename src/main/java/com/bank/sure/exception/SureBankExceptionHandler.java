package com.bank.sure.exception;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.bank.sure.exception.message.ErrorMessage;
@ControllerAdvice
public class SureBankExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        
    }
    
    
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
        
    }
    
    
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.FORBIDDEN);
        
    }
    
    
    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
        
    }
    
    
    @ExceptionHandler(BalanceNotAvailableException.class)
    protected ResponseEntity<Object> handleBalanceNotAvailableException(BalanceNotAvailableException ex, WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
        
    }
    
@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
@Override
protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
}
@Override
protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
    ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
}
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        List<String> errors=ex.getBindingResult().getFieldErrors().stream().
                map(x->x.getDefaultMessage()).collect(Collectors.toList());
        
        ErrorMessage errorMessage=new ErrorMessage(new Date(),errors.get(0),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
    
        
}