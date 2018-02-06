import java.util.ArrayList;

public class ChessBoard {
    //Holds array list of players
    private ArrayList<Player> players;
    private int currentPlayer;
    static int X_RANGE_LOWER = 0;
    static int X_RANGE_UPPER = 8;
    static int Y_RANGE_LOWER = 0;
    static int Y_RANGE_UPPER = 8;
    //Methods
    public ChessBoard(){
        this.players = new ArrayList<>();
        this.players.add(new Player(this,1));
        this.players.add(new Player(this,-1));
        this.currentPlayer = 0;
        setUp();
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

    public boolean isLocEmpty(Location loc){
        for(Player player : players){
            if(player.pieceAt(loc)!=null){
                return false;
            }
        }
        return true;
    }

}
