package ca.mcgill.ecse321.library.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public CustomerException(String msg){
        super(msg);
    }
}
