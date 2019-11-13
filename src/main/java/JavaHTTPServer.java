import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;


public class JavaHTTPServer implements Runnable{

    static final int PORT = 8080;

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
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileName = null;
        String inputText = null;

        try{
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            String input = in.readLine();
            StringTokenizer parse = new StringTokenizer(input);
            String method = parse.nextToken().toUpperCase();
            fileName = parse.nextToken().toLowerCase();

            if(method.equals("POST")) {
                //post function
            }else if (method.equals("PUT")){
                JavaHTTPPUT.PUT(fileName, out);
            }else if (method.equals("GET")){
                //function
            }else if (method.equals("DELETE")){
                //function
            }else{
                //Om method inte finns
            }


        }catch (IOException e){
            System.err.println(e);
        }

    }
}