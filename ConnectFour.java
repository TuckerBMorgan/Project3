import java.io.IOException;

public class ConnectFour
{
    public int localNumber;
    public String localName;
    
    public int awayNumber;
    public String awayName;
    public Client client;
    public boolean isYourTurn;
    C4UI c4ui;
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
        client = cli;
        C4Board c4Board = new C4Board();
        C4UI iu = new C4UI(c4Board, playername);
        c4ui = iu;
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
                localNumber = holdInt;
            }
            else
            {
                awayNumber = holdInt;
            }
            
            if(holdInt == 1)
            {
                isYourTurn = true;   
            }
            else
            {
                isYourTurn = false;
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
            c4ui.OnTurnChange(!isYourTurn);   
        }
        else if(parts[0] == "add")
        {
            
        }
        else if(parts[0] == "clear")
        {
            
        }
    }
    
    public void ReportMove(int col)
    {
        String message = "add " + localNumber + " " + col + "\n";
        client.SendMessage(message); 
    }
    
    public void ClearGame()
    {
        client.SendMessage("clear");
    }
}