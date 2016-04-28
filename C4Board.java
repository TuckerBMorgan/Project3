public class C4Board implements C4BoardIntf
{
    public static int MARKER_FOR_1 = 1;
    public static int MARKER_FOR_2 = 2;
    
    public int[][] board = new int[C4BoardIntf.COLS][C4BoardIntf.ROWS];
    
    public boolean hasPlayer1Marker(int r, int c)
    {
        return board[c][r] == MARKER_FOR_1;
    }
    
    public boolean hasPlayer2Marker(int r, int c)
    {
        return board[c][r] == MARKER_FOR_2;
    }
    
    public int[] hasWon()
    {
        int[] i = new int[4];
        return i;
    }
}