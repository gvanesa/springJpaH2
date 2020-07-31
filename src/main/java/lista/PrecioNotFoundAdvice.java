package lista;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

class PrecioNotFoundAdvice {

   @ResponseBody
   @ExceptionHandler(PrecioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String precioNotFoundHandler(PrecioNotFoundException ex){
       return ex.getMessage();
   }


}
