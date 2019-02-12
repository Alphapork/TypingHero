
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
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


class Server extends Thread{
    Socket s;

    public Server(Socket s)
    {
	this.s = s;
    }
    public void  run()
    {
	System.out.println("ServerSocket created");
       	try
	    {
			
		//Output in file
		
	       	DataInputStream textin = new DataInputStream(s.getInputStream());
			DataOutputStream textout = new DataOutputStream(s.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt",true));
		while(true)
	       	{
       			String input = (String)textin.readUTF();
				writer.append(input + '\n');	
				   
       		}
			
			
			
			/* (Output in console)
			DataInputStream textin = new DataInputStream(s.getInputStream());
			DataOutputStream response = new DataOutputStream(s.getOutputStream()); 
			while(true)
		    {
			String input = (String)textin.readUTF();
			System.out.println(input);
			response.writeUTF("response");
			response.flush();
			
		    }   
			*/			
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
