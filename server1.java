import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server1{

    private static Socket socket;

    public static void main(String[] args){
        try{

            ServerSocket serverSocket = new ServerSocket(40736);
            System.out.println("The server is ready to receive");
            int count=0;
            while(true){
                /* RECEIVING MESSGE FROM THE CLIENT */
                socket = serverSocket.accept();
                if(count == 0){
                System.out.println("Connected by client on ['127.0.0.1', 40736]");
                count++;
            }
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                if(number.equals("0/0")){
                        try{
                            socket.close();
                             System.out.println("Server program ends (due to the client closing the connection).");
                            System.exit(0);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                System.out.print("Received question " + "'" +number+"'; ");

                /* PERFORMING THE MATHEMATICAL OPERATION */
                String returnMessage;
                try{
                    int numberInIntFormat = Integer.parseInt(number);
                    int returnValue = numberInIntFormat*2;
                    returnMessage = String.valueOf(returnValue) + "\n";
                    System.out.println("send back answer " + returnMessage);
                }catch(NumberFormatException e){
                    returnMessage = "Invalid input. Please try again.\n";
                }

                /* SENDING ANSWER BACK TO THE CLIENT */
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                bw.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                /* CLOSING THE SOCKET */
                socket.close();
            }catch(Exception e){}
        }
    }
}