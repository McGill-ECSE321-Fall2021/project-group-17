package ca.mcgill.ecse321.library.service.Exception;

public class LibrarianException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public LibrarianException (String msg){
        super(msg);
    }
}
