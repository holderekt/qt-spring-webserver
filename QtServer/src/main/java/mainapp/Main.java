package mainapp;

import server.MultiServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length == 1){
            int PORT;
            try{
                PORT = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                PORT = 8080;
            }
            new MultiServer(PORT);
        }else{
            new MultiServer();
        }
    }
}
