package clioutputmanager;


import java.util.LinkedList;
import java.util.List;

/**
 *  Gestione dei menu per UI basate su command line
 *  Utilizza IOManager per la lettura da tastiera
 */
public class Menu {
    /**
     * Messaggio da visualizzare prima delle opzioni
     */
    private String description = "";

    /**
     * Elenco delle opzioni disponibili
     */
    private List<String> menuEntries = new LinkedList<>();

    /**
     *  Costruttore
     * @param description Messaggio da visualizzzare
     * @param menuEntries Elenco delle opzioni
     */

    public Menu(String description, String[] menuEntries){
        this.description = description;
       for(String option : menuEntries){
           this.menuEntries.add(option);
       }
    }

    /**
     * Aggiunge una opzioni all'elenco delle opzioni
     * @param option Opzione da aggiugere
     */
    public void addOption(String option){
        this.menuEntries.add(option);
    }

    /**
     * Visualizza tutte le opzioni e legge da tastiera la scelta
     * dell'utente
     * @return Posizione della opzione scelta (Numerate da 1 al numero di opzioni presenti)
     */

    public int chooseOption(){
        System.out.println("\n" + description);
        int currentOption = 1;
        for(String option : menuEntries){
            System.out.println("["+ (currentOption++) +"] "+ option);
        }
        System.out.println("");
        return IOManager.readInt("Choose one option (1-" + menuEntries.size() + ")", 1, menuEntries.size());
    }
}
