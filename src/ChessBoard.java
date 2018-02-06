import java.util.ArrayList;

public class ChessBoard {
    //Holds array list of players
    private ArrayList<Player> players;
    private int currentPlayer;

    //Methods:
    public ChessBoard(){

    }

    public void setUp(){

    }

    public void tearDown(){

    }

    public int getCurrentPlayerNum(){
        return 0;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public Player getPlayer(int playerNum){
        return players.get(playerNum);
    }

    public void movePiece(Location pieceLoc, Location destLoc){

    }

    public void movePiece(GamePiece piece, Location destLoc){

    }

    public boolean canMovePiece(Location pieceLoc, Location destLoc){
        return true;
    }

    public boolean canMovePiece(GamePiece piece, Location destLoc){
        return true;
    }

    public GamePiece pieceAt(Location pieceLoc){
        return null;
    }


}
