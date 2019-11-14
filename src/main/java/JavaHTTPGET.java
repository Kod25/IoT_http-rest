import java.io.*;

public class JavaHTTPGET {

    public static void GET(String fileName, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        File file = new File("../src/main/resources/" + fileName + ".txt");
        if(file.exists()){
            int fileLength = (int)file.length();
            byte[] data = new byte[fileLength];
            FileInputStream fileIn = new FileInputStream(file);
            fileIn.read(data);
            fileIn.close();

            out.println("HTTP/1.1 200 OK");
            out.println("Server: Java HTTP Server from Wirre and Dalle : 1.0");
            out.println("Content-type: text/html");
            out.println();
            out.flush();
            dataOut.write(data, 0, fileLength);
            dataOut.flush();
        } else {
            out.println("HTTP/1.1 404 File not found");
            out.println("Server: Java HTTP Server from Wirre and Dalle : 1.0");
            out.println();
            out.flush();
        }
    }
}
