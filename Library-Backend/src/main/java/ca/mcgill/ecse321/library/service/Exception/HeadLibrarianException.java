package ca.mcgill.ecse321.library.service.Exception;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class HeadLibrarianException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public HeadLibrarianException(String msg){
        super(msg);
    }
}
