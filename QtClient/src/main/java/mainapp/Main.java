package mainapp;


import client.Client;
import clioutputmanager.IOManager;
import keyboard.Keyboard;

import java.io.IOException;

public class Main {


    public static void main(String[] paramArrayOfString) {
        String DEFAULT_ARG_1 = "localhost";
        int DEFAULT_ARG_2 = 8080;

        String IP;
        int  PORT;

        if(paramArrayOfString.length == 2){
            IP = paramArrayOfString[0];
            PORT = Integer.parseInt(paramArrayOfString[1]);
        }else{
            IP = DEFAULT_ARG_1;
            PORT = DEFAULT_ARG_2;
        }

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
