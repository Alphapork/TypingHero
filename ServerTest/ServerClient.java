
import java.io.*;
import java.io.OutputStreamWriter.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerClient {
    public static void main (String[] arg)
    {
	ServerSocket ss = null;
	Socket s = null;
	System.out.println("Server will run until user type quit");
	try{
	     ss = new ServerSocket(9999);
	     System.out.println("Server now running");
	}catch (Exception e)
	    {
		System.out.println(e);
	    }
	CommandlineScanner Scan = new CommandlineScanner("Scanner");
	Scan.start();
	while(true)
	    {
		try{
		    s = ss.accept();
		    Server st = new Server(s);
		    st.start();
		}
		catch(Exception e)
		    {
			System.out.println(e);
		    }
	    }
    }
}


class Server extends Thread {
	Socket s;
	//För file inläsning
        public final static String FILE_TO_RECEIVE = "test.txt";
	public final static int FILE_SIZE = 10000; // TEMP FILE SIZE MUST BE BIGGER THEN ACUALLY FILE SIZE



    public Server(Socket s)
    {
	this.s = s;
    }
    public void  run()
    {
	System.out.println("ServerSocket created");
       	try
	    {
			byte[] fileSize = new byte[FILE_SIZE];
			InputStream input = s.getInputStream();
			FileOutputStream fileOut = new FileOutputStream(FILE_TO_RECEIVE);
			BufferedOutputStream fileBOut = new BufferedOutputStream(fileOut);
			int bytesread = input.read(fileSize, 0, fileSize.length);
			int current = bytesread;

			do

			{
				bytesread = input.read(fileSize, current, fileSize.length - current);
				if (bytesread >= 0) {
					current += bytesread;
				}
			}while(bytesread >-1);

			fileBOut.write(fileSize,0,current);
			fileBOut.flush();
			System.out.println("File " + FILE_TO_RECEIVE + " downloaded(" + current + " bytes read)");
			fileBOut.close();
			fileOut.close();
			//Skicka response
			DataOutputStream response = new DataOutputStream(s.getOutputStream());
			response.writeUTF("Fil mottagen");
			response.flush();
	    }
       	catch (Exception e)
       	    {
       		System.out.println(e);
	    }
    }
}

class CommandlineScanner implements Runnable{
    private Thread t;
    private String name;

    public CommandlineScanner(String name)
    {
	this.name = name;
    }
			     
    public void run()
    {
	System.out.println("CommandlineScanner created");
	Scanner scan = new Scanner(System.in);
	if(scan.nextLine().equals("quit"))
	   {
	       System.exit(0);
	   }
    }
	public void start()
    {
	if(t==null)
	    {
		System.out.println("CommandlineScanner now starting");
		t = new Thread(this,name);
		t.start();
	    }
    }
}
