package ca.mcgill.ecse321.library.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends  RuntimeException{
    private static final long serialVersionUID = 1l;

    public NotFoundException (String msg){
        super(msg);
    }
}
