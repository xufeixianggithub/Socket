package com.xufx.SocketClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        int port=8089;
        String host="localhost";

        Socket socket=new Socket(host,port);

        DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());

        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

        Scanner sc=new Scanner(System.in);

        boolean flag=false;
        while (!flag){
            System.out.println("请输入正方形的边长:");
            int length=sc.nextInt();
            dataOutputStream.writeInt(length);

            dataOutputStream.flush();

            double area=dataInputStream.readDouble();

            System.out.println("服务器返回的计算面积为:" + area);

            while (true){
                System.out.println("继续计算？(Y/N)");

                String str=sc.next();

                if (str.equalsIgnoreCase("N")){
                    dataOutputStream.close();
                    flag=true;
                    break;
                }else if(str.equalsIgnoreCase("Y")){

                    dataOutputStream.writeInt(1);
                    dataOutputStream.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}
