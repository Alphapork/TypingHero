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
}
