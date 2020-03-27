/**
 * Webserver Spring Boot
 * @author Ivan Diliso
 */

package webserver;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.EmptyTypeException;
import database.NoValueException;
import json.JSONWrongTypeException;
import jsonconverter.JSONConverter;
import mining.ClusteringRadiusException;
import mining.QTMiner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Spring Controller che gestisce la visualizzazzione delle pagine web e la
 * computazione dei delle risposte dalle pagine web
 */

@Controller
@Scope("session")
public class DatabaseClusteringInputController {

    /**
     * Database dataset
     */
    private Data data = null;

    /**
     * Qt Clustering
     */
    private QTMiner miner = null;

    /**
     * Raggio clustering
     */
    private Double radius = 0.0;

    /**
     * Nome tabella database
     */
    private String tablename = "";


    /*
        Visualizzazzione pagine web
    */


    /**
     * Reindirizza alla pagina web "/index" se
     * connessi all'indirizzo "/"
     *
     * @return RedirectView
     */

    @RequestMapping(value="/")
    public RedirectView redirectRoot(){
        return new RedirectView("index");
    }

    /**
     * Fornisce la pagina web per la selezione della tipologia di dati
     * da caricare (Database o file)
     *
     * @return ModelAndView Costruita con la view "index.jsp"
     */

    @RequestMapping(value="index")
    public ModelAndView viewIndex(){
        return new ModelAndView("index");
    }

    /**
     * Fornisce la pagina web per l'inserimento dei dati per il clustrering
     * da database (Raggio e nome tabella)
     *
     * @return ModelAndView Costruita con la view "database_cluster.jsp"
     */

    @RequestMapping(value="/loaddatabase")
    public ModelAndView viewDatabaseCluster()  {
       return new ModelAndView("database_cluster");
    }

    /**
     * Fornisce la pagina web per l'inserimento del nome del file da quale
     * caricare i risultati del clusteing
     *
     * @return ModelAndView Costruita con la view "file_cluster.jsp"
     */

    @RequestMapping(value="/loadfile")
    public ModelAndView viewFileLoad(){
        return new ModelAndView("file_cluster");
    }

    /*
        Pagine web di visualizzazzione risultati
     */

    /**
     * Carica i dati dal database, esegue il clustering e fornisce la pagina
     * web che visualizza i risultati. Se viene generato errore verrà
     * fornita una pagina contenente il messaggio di errore.
     *
     * @param param_radius Raggio clustering
     * @param param_tablename Nome tabella database
     * @return model
     */

    @RequestMapping(value="/databaseresult", method = RequestMethod.POST, params = {"radius", "tablename"})
    public ModelAndView viewResult(
            @ModelAttribute("radius") Double param_radius,
            @ModelAttribute("tablename") String param_tablename)  {


        ModelAndView model = null;
        radius = param_radius;
        tablename = param_tablename;
        miner = new QTMiner(radius);

        try{

            data = new Data(tablename);
            miner.compute(data);

            model = new ModelAndView("database_result");
            model.addObject("msg", tablename);


        } catch (DatabaseConnectionException | NoValueException | SQLException | EmptyTypeException e) {

            model = new ModelAndView("errorpage");
            String message;

            if(e.getMessage() == null){
                message = "Could not retrieve data from database";
            }else{
                message = e.getMessage();
            }

            model.addObject("error_message", message);

        }  catch (ClusteringRadiusException | EmptyDatasetException e ) {

            model = new ModelAndView("errorpage");
            String message;

            if(e.getMessage() == null){
                message = "Could not learn from data";
            }else{
                message = e.getMessage();
            }

            model.addObject("error_message", message);
        }

        return model;
    }

    /**
     * Fornisce la pagina che visualizza i risultati del clustering su
     * dati caricati da database. Se non é stata ancora effettuato il
     * caricamento dei dati e il clustering verrà fornita una pagina
     * contenente il messaggio di errore.
     *
     * @return model
     */

    @RequestMapping(value="/databaseresult")
    public ModelAndView viewResult() {
        ModelAndView model;

        if(data != null && miner != null && !miner.isLoadedFromFile()){
            model = new ModelAndView("database_result");
            model.addObject("msg", tablename);
        }else{
            model = new ModelAndView("errorpage");
            model.addObject("error_message", "No computation made yet");
        }

        return model;
    }

    /**
     * Carica i risultati del clustering da un file e fornisce la pagina web
     * che visualizza i risultati. Se viene generato errore verrà fornita una
     * pagina web contenente il messaggio di errore.
     *
     * @param param_filename Nome del file da caricare
     * @return model
     */

    @RequestMapping(value="/fileresult", params = "filename")
    public ModelAndView viewFileResult(
            @ModelAttribute("filename") String param_filename) {

        try{
            data = null;
            miner = new QTMiner(param_filename + ".dmp");
            return new ModelAndView("file_result");

        } catch (IOException | ClassNotFoundException e) {

            ModelAndView errorPage = new ModelAndView("errorpage");
            String message;

            if(e.getMessage() == null){
                message = "Could not load file";
            }else{
                message = e.getMessage();
            }

            errorPage.addObject("error_message", message);
            return errorPage;
        }


    }

    /**
     * Fornisce la pagina web che visualizza i risultati del clustering
     * caricati tramite file. Se non é stata eseguita alcuna computazione
     * tramite caricamento file verrà fornita una pagina web contente
     * un messaggio di errore.
     *
     * @return model
     */

    @RequestMapping(value="/fileresult")
    public ModelAndView viewFileResult() {

        ModelAndView model;

        if(miner != null && miner.isLoadedFromFile()){
            model =  new ModelAndView("file_result");
        }else{
            model = new ModelAndView("errorpage");
            model.addObject("error_message", "No computation made yet");
        }

        return model;
    }

    /*
       Pagine web per il salvataggio del clustering su file
     */

    /**
     * Fornisce la pagina web per l'inserimento del nome del file in cui
     * salvare la computazione. Se non é stata eseguita alcuna computazione
     * verrà fornita una pagina web contenente il messaggio di errore.
     *
     * @return model
     */

    @RequestMapping(value="saveresult")
    public ModelAndView viewResultSave()  {

        ModelAndView model;

        if(data != null && miner != null){
            model = new ModelAndView("save_cluster");

        }else{
            model = new ModelAndView("errorpage");
            model.addObject("error_message", "No computation made yet");
        }

        return model;
    }

    /**
     * Salva i risultati del clustering su file e fornisce la pagina web che
     * visualizza il successo dell'operazione eseguita. Se viene generato errore
     * verrà fornita la pagina web contenente il messaggio di errore.
     *
     * @param param_filename Nome del file sul quale salvare la computazione
     * @return model
     */

    @RequestMapping(value="saveresult", params = "filename")
    public ModelAndView viewResultSave(
            @ModelAttribute("filename") String param_filename) {

        ModelAndView model = null;
        try{

            if(data != null && miner != null){
                miner.salva(param_filename + ".dmp");
                model = new ModelAndView("success");
            }else{
                model = new ModelAndView("errorpage");
                model.addObject("error_message", "No computation made yet");
            }

        } catch (IOException e) {
            model = new ModelAndView("errorpage");
            String message;

            if(e.getMessage() == null){
                message = "Could not load file";
            }else{
                message = e.getMessage();
            }

            model.addObject("error_message", message);
        }

        return model;
    }


    /*
       JSON Mapping pages
     */

    /**
     * Fornisce il JSON contenente il dataset
     * @return Dataset
     */

    @RequestMapping(value="/dataset")
    public @ResponseBody String mapData() {
        if(data != null){
            try {
                return JSONConverter.convert(data);
            } catch (JSONWrongTypeException e) {
                e.printStackTrace();
            }

        }else{
            return null;
        }
        return null;
    }

    /**
     * Fornisce il JSON contenente il risultato del clustering
     * @return ClusterData
     */

    @RequestMapping(value="/clusterdata")
    public @ResponseBody String mapClusterData(){
        if(miner!= null && data != null){
            return JSONConverter.convert(miner.getC(), data);

        }else{
            return null;
        }
    }

    /**
     * Fornisce il JSON contenente i centroidi caricati da file
     * @return DataSet
     */

    @RequestMapping(value="/fileclusterinfo")
    public @ResponseBody String mapClusterFileInfo() {

        if(miner != null && miner.isLoadedFromFile()){

            try{
                return JSONConverter.convert(miner.getCentroids());
            }catch (JSONWrongTypeException e){
                return null;
            }
        }else{
            return null;
        }
    }
}