package com.example.oskarnoko.einzelbeispielse2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by oskarnoko on 14.03.2021.
 */
public class Client implements Runnable{

    private Socket socket;

    public void createClient(int port, String hostname) throws IOException {
        socket = new Socket(hostname, port);
    }

    public void sendMessageFromClient(String message) throws IOException{
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());

        outToServer.writeBytes(message + '\n');
    }

    public String retrieveDataFromClient() throws IOException{
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String output = inFromServer.readLine();

        return output;
    }

    public void closeClientSocket() throws IOException {
        socket.close();
    }

    @Override
    public void run() {
        try {
            createClient(53212, "se2-isys.aau.at");
            sendMessageFromClient(MainActivity.matrikelnr);
            MainActivity.receiver = retrieveDataFromClient();
            closeClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
