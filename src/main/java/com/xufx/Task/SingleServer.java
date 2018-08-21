package com.xufx.Task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SingleServer implements Runnable{
    private Socket socket;
    private int clientNo;


    public SingleServer(Socket socket,int clientNo){
        this.socket=socket;
        this.clientNo=clientNo;
    }


    public void run() {
        try {
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

            do{
                double length=dataInputStream.readInt();

                System.out.println("从客户端" + clientNo + "接收到的边长数据为：" + length);

                double result=length*length;

                dataOutputStream.writeDouble(result);

                dataOutputStream.flush();

            }while (dataInputStream.readInt()!=0);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("与客户端" + clientNo + "通信结束");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }
}
