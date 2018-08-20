package com.xufx.SocketServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        int port=8089;
        ServerSocket socketServer=new ServerSocket(port);
        Socket socket=socketServer.accept();

        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());

        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

        do{
            int length=dataInputStream.readInt();

            System.out.println("服务器端收到的边长数据为：" + length);

            dataOutputStream.writeDouble(length*length);

            dataOutputStream.flush();

        }while (dataInputStream.readInt()!=0);


        socket.close();
        socketServer.close();
    }
}
