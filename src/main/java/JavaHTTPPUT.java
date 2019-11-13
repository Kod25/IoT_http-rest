import java.io.*;

public class JavaHTTPPUT {

    public static void PUT(String fileName, PrintWriter out) throws IOException {
        String[] split = fileName.split("/");
        File file = new File("../src/main/resources" + split[1] + ".txt");
        if(false){
            //Appends to file.
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(split[2]);
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
