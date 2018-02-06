import java.util.ArrayList;

public class ChessBoard {
    //Holds array list of players
    private ArrayList<Player> players;
    private int currentPlayer;
    static int X_RANGE_LOWER = 0;
    static int X_RANGE_UPPER = 7;
    static int Y_RANGE_LOWER = 0;
    static int Y_RANGE_UPPER = 7;
    //Methods
    public ChessBoard(){
        this.players = new ArrayList<>();
        this.players.add(new WhitePlayer(this));
        this.players.add(new BlackPlayer(this));
        this.currentPlayer = 0;
        setUp();
    }

    public void setUp(){
        for(Player player : players){
            player.setUp();
        }
    }

    public void tearDown(){
        for(Player player : players){
            player.tearDown();
        }
    }

    public int getCurrentPlayerNum(){
        return currentPlayer;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public Player getPlayer(int playerNum){
        return players.get(playerNum);
    }

    public void movePiece(Location pieceLoc, Location destLoc) throws IllegalMoveException{
        GamePiece piece = pieceAt(pieceLoc);
        if(piece == null){
            throw new IllegalMoveException("No game piece at that location");
        }
        if(piece.getPlayer() == getCurrentPlayer()){
            piece.moveTo(destLoc);
        }
        currentPlayer++;
        currentPlayer %= players.size();
    }

    public void movePiece(GamePiece piece, Location destLoc) throws IllegalMoveException{
        if(piece.getPlayer() == getCurrentPlayer()){
            piece.moveTo(destLoc);
        }
        currentPlayer++;
        currentPlayer %= players.size();
    }

    public boolean canMovePiece(Location pieceLoc, Location destLoc){
        GamePiece piece = pieceAt(pieceLoc);
        if(piece == null){
            return false;
        }
        if(piece.getPlayer() == getCurrentPlayer()){
            return piece.canMoveTo(destLoc);
        }
        return false;
    }

    public boolean canMovePiece(GamePiece piece, Location destLoc){
        if(piece.getPlayer() == getCurrentPlayer()){
            return piece.canMoveTo(destLoc);
        }
        return false;
    }

    public GamePiece pieceAt(Location pieceLoc){
        for(Player player : players){
            if(player.pieceAt(pieceLoc)!=null){
                return player.pieceAt(pieceLoc);
            }
        }
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
