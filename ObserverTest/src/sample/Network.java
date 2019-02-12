package sample;
import java.io.*;
import java.net.*;

public class Network {

    private Socket client;

    public Network() {
        try{
            client  = new Socket("90.229.141.157", 9999);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void send(){
        try {
            DataOutputStream sending = new DataOutputStream(client.getOutputStream());
            sending.writeUTF("Testar");
            sending.flush();
            DataInputStream response = new DataInputStream(client.getInputStream());
            String responseFrom = (String) response.readUTF();
            System.out.println(responseFrom);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void sendFile()
    {
        try{
            File testFile = new File("testfil.txt");
            byte [] filesize = new byte[(int)testFile.length()];
            FileInputStream fileS = new FileInputStream(testFile);
            BufferedInputStream fileB = new BufferedInputStream(fileS);
            fileB.read(filesize,0,filesize.length);
            OutputStream output = client.getOutputStream();
            System.out.println("Sending file(" + filesize.length + " bytes)");
            output.write(filesize,0,filesize.length);
            output.flush();
            System.out.println("Client done");
            DataInputStream response = new DataInputStream(client.getInputStream());
            String serverresponse = (String) response.readUTF();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
