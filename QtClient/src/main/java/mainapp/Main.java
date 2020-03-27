package mainapp;


import client.Client;
import clioutputmanager.IOManager;
import keyboard.Keyboard;

import java.io.IOException;

public class Main {

    public static void main(String[] paramArrayOfString) {

        String IP = paramArrayOfString[0];
        int PORT = (new Integer(paramArrayOfString[1])).intValue();

        Client client = null;
        try {
            client = new Client(IP, PORT);
        } catch (IOException iOException) {
            System.out.println(iOException.getMessage());
            return;
        }

        client.mainLoop();
    }
}
