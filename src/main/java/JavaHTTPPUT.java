import java.io.*;

public class JavaHTTPPUT {

    public static void PUT(String fileName, PrintWriter out, String body) throws IOException {
        File file = new File("../src/main/resources/" + fileName + ".txt");
        if(file.exists()){
            //Appends to file.
            FileWriter fr = new FileWriter(file, false);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(body);
            pr.close();
            br.close();
            fr.close();

            out.println("HTTP/1.1 201 File updated");
            out.println("Server: Java HTTP Server from Wirre and Dalle : 1.0");
            out.println();
            out.flush();
        } else {
            out.println("HTTP/1.1 404 File not found");
            out.println("Server: Java HTTP Server from Wirre and Dalle : 1.0");
            out.println();
            out.flush();
        }
    }
}
