package com.xufx.SocketServer;

import com.xufx.Task.SingleServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        int port=8089;

        int clientNo=1;


        ServerSocket socketServer=new ServerSocket(port);
        ExecutorService exec= Executors.newCachedThreadPool();

        try{
            while (true){
                Socket socket=socketServer.accept();
                exec.execute(new SingleServer(socket,clientNo));
                clientNo++;
                System.out.println("clientNo"+clientNo);
            }
        }finally {
            socketServer.close();
        }
    }
}
