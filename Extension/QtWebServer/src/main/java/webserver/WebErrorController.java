package webserver;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * MVC Controller per error handling
 * @author Ivan Diliso
 */

@Controller
public class WebErrorController implements ErrorController {

    /**
     * Fornisce una pagina web per visualizzare i messaggi di errore
     * inaspettati durante l'esecuzione del webserver. Se l'errore non fornisce
     * un messaggio verrò visualizzato un messaggio generico.
     *
     * @param request Richiesta HTTP generata dall'errore
     * @return model
     */

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        ModelAndView model = new ModelAndView("errorpage");

        if(exception != null){
            model.addObject("error_message", exception.getMessage() );
        }else{
            model.addObject("error_message", "Could not handle request" );
        }

        return model;
    }

    /**
     * Ritorna la pagina web custom "/error" quando viene generato un errore
     * @return Error page
     */

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
