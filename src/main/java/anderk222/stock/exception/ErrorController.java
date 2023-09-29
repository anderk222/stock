package anderk222.stock.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ErrorController {

    // private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    // @ExceptionHandler(Throwable.class)
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String exception(final Throwable throwable, final Model model) {

        
    //     String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        
    //     throwable.printStackTrace();

    //     model.addAttribute("errorMessage", errorMessage);
    //     return "error";
    // }

    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerRuntimeException( RuntimeException exception, Model model ){
    
        model.addAttribute("errorMessage", exception.getMessage());

        System.out.println("\n\n\n\n");
        System.out.println(exception.getMessage());

        return "error";

    }

}