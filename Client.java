import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Client implements Runnable
{
    Socket socket;
    Reading reading;
    Writing writing;
    
    public Client()
    {
        
    }   
    
    public void Setup(String ip, int port, ConnectFour c4) throws IOException
    {
        socket = new Socket(ip, port);
        reading = new Reading();
        reading.Setup(socket, c4);
        writing = new Writing();
        writing.Setup(socket);
    } 
    
    public void run()
    {
        while(true)
        {
            writing.run();
            reading.run();
        }
    }
    
    public void SendMessage(String str)
    {
        writing.SendMessageInSide(str);
    }
    
    
    private class Reading
    {
        private BufferedReader bufferedReader;
        private String inMessage;
        private ConnectFour c4;
        
        public void Setup(Socket socket, ConnectFour c4) throws IOException
        {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.c4 = c4;
        }
        
        public void run()
        {
            try
            {
                if(bufferedReader.availble <= 0)
                    return;
                while((inMessage = bufferedReader.readLine()) != null)
                {
                    c4.ReportMessage(inMessage);
                }
            }
            catch(IOException ioe)
            {
                System.out.print("IOException\n");
            }
        }
    }

    private class Writing
    {
        private PrintWriter printWriter;
        private String str;
        
        public void Setup(Socket socket) throws IOException
        {
            printWriter = new PrintWriter(socket.getOutputStream(), true);   
        }
        
        public void run()
        {
            if(str != null)
            {
                printWriter.println(str);
                str = null;
            }
        }
        
        public void SendMessageInSide(String message)
        {
            str = message;
        }
    }
    
}
