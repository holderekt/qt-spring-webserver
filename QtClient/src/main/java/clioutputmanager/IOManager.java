package clioutputmanager;

import keyboard.Keyboard;

/**
 * Classe di utility per la lettura con controlli da tastiera di double
 * stringhe e interi
 * Utilizza Keyboard per la lettura da tastiera
 */
public class IOManager {

    /**
     * Lettura continua di un double da tastiera finchè non
     * viene inserito un valore esatto
     * @param message Messaggio da visualizzare prima di leggere il double
     * @return Valore double letto correttamente
     */
    public static Double readDouble(String message){
        Keyboard.setPrintErrors(false);
        System.out.print(message + ": ");
        Double d;
        do{
            d = Keyboard.readDouble();
        }while(Double.isNaN(d));
        return d;
    }

    /**
     *  Lettura continua di un double da tastiera finchè non viene insertio un valore esatto
     *  contenuto tra un minimo e un massimo
     * @param message Messaggio da visualizzare prima di leggere il double
     * @param min Minimo valore consentito
     * @param max Massimo valore consentito
     * @return Double letto correttamente
     */
    public static Double readDouble(String message, Double min, Double max){
        Keyboard.setPrintErrors(false);
        System.out.print(message + ": ");
        Double d;
        do{
            d = Keyboard.readDouble();
        }while(Double.isNaN(d) || d < min || d > max);
        return d;
    }

    /**
     *  Lettura di una stringa da tastiera
     * @param message Messaggio da visualizzare prima di leggere la stringa
     * @return Stringa letta
     */
    public static String readString(String message){
        Keyboard.setPrintErrors(false);
        System.out.print(message + ": ");
        return Keyboard.readString();
    }

    /**
     * Lettura continua di un intero da tastiera finchè non
     * viene inserito un valore esatto
     * @param message Messaggio da visualizzare prima di leggere l'intero
     * @return Valore intero letto correttamente
     */
    public static int readInt(String message){
        Keyboard.setPrintErrors(false);
        System.out.print(message + ": ");
        int d;

        do{
            d = Keyboard.readInt();
        }while(d == Integer.MIN_VALUE);

        return d;
    }

    /**
     *  Lettura continua di un intero da tastiera finchè non viene insertio un valore esatto
     *  contenuto tra un minimo e un massimo
     * @param message Messaggio da visualizzare prima di leggere l'intero
     * @param min Minimo valore consentito
     * @param max Massimo valore consentito
     * @return Intero letto correttamente
     */
    public static int readInt(String message, int min, int max){
        Keyboard.setPrintErrors(false);
        System.out.print(message + ": ");
        int d;
        do{
            d = Keyboard.readInt();
        }while(d == Integer.MIN_VALUE || d < min || d > max);
        return d;
    }
}
