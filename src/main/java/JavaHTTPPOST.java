import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaHTTPPOST {
    public static void POST(String filename, PrintWriter out) throws IOException {
        File file = new File("../src/main/resources/"+filename+".txt");
        if(file.createNewFile()){
            // send HTTP Headers
            out.println("HTTP/1.1 201 Successfully created");
            out.println("Server: Java HTTP Server from Wictor and Dalle : 1.0");
            out.println(); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
        }else{
            out.println("HTTP/1.1 409 sensor already exists");
            out.println("Server: Java HTTP Server from Wictor and Dalle : 1.0");
            out.println(); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
        }
    }
}
