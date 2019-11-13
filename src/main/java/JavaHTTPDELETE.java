import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaHTTPDELETE {
    public static void DELETE(String filename, PrintWriter out) throws IOException {
        File file = new File("../src/main/resources/"+filename+".txt");
        if(file.delete()){
            // send HTTP Headers
            out.println("HTTP/1.1 200 successfully deleted");
            out.println("Server: Java HTTP Server from Wictor and Dalle : 1.0");
            out.println(); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
        }else{
            out.println("HTTP/1.1 404 file not found");
            out.println("Server: Java HTTP Server from Wictor and Dalle : 1.0");
            out.println(); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
        }
    }

}
