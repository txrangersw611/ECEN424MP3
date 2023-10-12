import java.io.*;
import java.net.*;

public class NaiveClient {
    public static void main(String[] args) throws Exception {
        //Arguments passed to function
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        

        Socket clientSock = new Socket(ip, port);

        InputStreamReader serverMsg = new InputStreamReader(clientSock.getInputStream());

        while (true) {
            //reads in individual lines, not buffer
            int msg = serverMsg.read();
            
            char cMsg = (char) msg;

            System.out.print(cMsg);

            if (cMsg == '\n'){
                clientSock.close();
                break;
            }

            //Here so client ends even if inputStream is bad
            if (msg == -1) {
                clientSock.close();
                break;
            }
        }
    }
}