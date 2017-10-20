import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client1{

    private static Socket socket;

    public static void main(String args[]){
        try{
            System.out.println("Attempting to connect to server...");

            Scanner scanner = new Scanner(System.in);
            String input=null;
            int count = 0;

            while(!"0/0".equals(input)){
            String host = "localhost";
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, 40736);
            
            
            if(count ==0){
            System.out.println("Connected with server on ['127.0.0.1', 40736]\n");
            count++;
        }
            /* SENDING QUESTION TO SERVER */
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

                System.out.println("Enter a basic math operation ('+', '-', '*', or '/'):");
                input = scanner.nextLine();

                String sendMessage = input + "\n";
                bw.write(sendMessage);
                bw.flush();

                /* ANSWER TO QUESTION FROM SERVER */
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String message = br.readLine();
                 if(message==null){
                    socket.close();
                    System.out.println("User input ends; end the client program.");
                    System.exit(0);
                }
                //System.out.println(input);
                System.out.println("Answer from server: " + message);
            }
        }catch (Exception exception){
            exception.printStackTrace();
    }

        try{
            /* CLOSING THE SOCKET */
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}