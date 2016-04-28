import java.io.IOException;

public class ConnectFour
{
    public int localNumber;
    public String localName;
    
    public int awayNumber;
    public String awayName;
    
    public static void main(String args[])
    {
        String host = args[0];
        String port = args[1];
        String playername = args[2];
        ConnectFour c4 = new ConnectFour();
        Client cli = new Client();
        try
        {
            cli.Setup(host, Integer.parseInt(port), c4);
        }
        catch(IOException io)
        {
            System.err.print("DIE\n");
        }
        C4Board c4Board = new C4Board();
        C4UI iu = new C4UI(c4Board, playername);
        cli.SendMessage("join " + playername);
        (new Thread(cli)).start();
    }
    
    public void ReportMessage(String message)
    {
        System.out.println(message);
        String[] parts = message.split(" ");
        if(parts.length < 2)
        {
            //We got some problems
        }
        
        if(parts[0] == "number")
        {
            int holdInt = Integer.parseInt(parts[1]);
            if(holdInt == 1)
            {
                localNumber = 1;
            }
            else
            {
                awayNumber = holdInt;
            }
        }
        else if(parts[0] == "new")
        {
            int holdInt = Integer.parseInt(parts[1]);
            if(holdInt == localNumber)
            {
                localName = parts[2];
            }
            else
            {
                awayName = parts[2];
            }
        }
        else if(parts[0] == "turn")
        {
            
        }
        else if(parts[0] == "add")
        {
            
        }
        else if(parts[0] == "clear")
        {
            
        }
    }
}