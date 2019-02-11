package sample;
import java.io.*;
import java.net.*;

public class Network {

    private Socket test;

    public Network() {
        try{
            test  = new Socket("90.229.141.157", 9999);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void send(){
        try {
            DataOutputStream text = new DataOutputStream(test.getOutputStream());
            text.writeUTF("Testar");
            text.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
