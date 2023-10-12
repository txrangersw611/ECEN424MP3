import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        //Arguments passed to function
        int port = Integer.parseInt(args[0]);
        int numClient = Integer.parseInt(args[1]);

        //Track client num
        int curClient = 0;

        //Get input from user
        BufferedReader inputVar = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter string: ");
        String msgStr = inputVar.readLine();

        System.out.println("Enter number of times: ");
        int msgInt = Integer.parseInt(inputVar.readLine());

        //Socket to take incoming messages on port number passed to function
        ServerSocket incomingSock = new ServerSocket(port);

        //Run loop numClient number of times
        while (curClient < numClient) {
            //Wait for connection from client and create output stream for data being sent to client
            Socket connectedSock = incomingSock.accept();

            DataOutputStream toClient = new DataOutputStream(connectedSock.getOutputStream());

            Runnable run = new Handler(toClient, msgInt, msgStr);
            Thread thr = new Thread(run);
            thr.start();

            curClient++;

            try {
                thr.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        incomingSock.close();
    }
}

class Handler implements Runnable {
    DataOutputStream toClient;
    int msgInt;
    String msgStr;

    public Handler(DataOutputStream toClient, int msgInt, String msgStr) {
        this.toClient = toClient;
        this.msgInt = msgInt;
        this.msgStr = msgStr;
    }

    public void run() {
        for (int i = 0; i < msgInt; i++) {
            try {
                if (i == msgInt-1) {
                    toClient.writeBytes(msgStr + "\n");
                }
                else {
                    toClient.writeBytes(msgStr);
                }
                Thread.sleep(1000);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}