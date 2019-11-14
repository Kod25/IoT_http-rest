import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;


public class JavaHTTPServer implements Runnable{

    static final int PORT = 80;

    private Socket connect;

    public JavaHTTPServer(Socket c){
        connect = c;
    }

    public static void main(String[] args) throws IOException {
        try {
            final ServerSocket server = new ServerSocket(PORT);
            System.out.println("listening");
            while (true) {
                JavaHTTPServer myServer = new JavaHTTPServer(server.accept());

                System.out.println("Connection opened. (" + new Date() + ")");

                Thread thread = new Thread(myServer);
                thread.start();
            }
        }catch(IOException e){
            System.err.println("Connection error" + e.getMessage());
        }
    }

    @Override
    public void run(){
        InputStream in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileName = null;
        String inputText = null;

        try{
            in = connect.getInputStream();
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());
            //System.out.println("Storlek: "+connect.getReceiveBufferSize());

            String data = "";
            char[] chars;
            while(connect.getInputStream().available()!=0){
                data = data + (char)in.read();
            }
            //System.out.println(data);

            String[] stringArray = data.split("\r\n\r\n");
            String[] arrayHead = stringArray[0].split("\r\n");
            String body = stringArray[1];

            System.out.println(body);
            String[] firstLine = stringArray[0].split(" ");
            String method = firstLine[0].toUpperCase();
            fileName = firstLine[1].toLowerCase();


            //System.out.println(fileName);

            if(method.equals("POST")) {
                JavaHTTPPOST.POST(fileName, out);
            }else if (method.equals("PUT")){
                JavaHTTPPUT.PUT(fileName, out, body);
            }else if (method.equals("GET")){
                //function
            }else if (method.equals("DELETE")){
                JavaHTTPDELETE.DELETE(fileName, out);
            }else{
                System.out.println("500 internt serverfel");
            }


        }catch (IOException e){
            System.err.println(e);
            
        }finally{
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close(); // we close socket connection
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }

                System.out.println("Connection closed.\n");
        }
    }
}