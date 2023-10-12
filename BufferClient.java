import java.io.*;
import java.net.*;

public class BufferClient {
    public static void main(String[] args) throws Exception {
        //Arguments passed to function
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        

        Socket clientSock = new Socket(ip, port);

        BufferedReader serverMsg = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

        String msg = serverMsg.readLine();

        System.out.println(msg);

        clientSock.close();

    }
}