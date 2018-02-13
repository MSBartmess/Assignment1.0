import java.util.ArrayList;

public class ChessBoard {
    private ArrayList<Player> players;
    private int currentPlayer;
    static int X_RANGE_LOWER = 0;
    static int X_RANGE_UPPER = 7;
    static int Y_RANGE_LOWER = 0;
    static int Y_RANGE_UPPER = 7;


    public ChessBoard(){
        this.players = new ArrayList<>();
        this.players.add(new WhitePlayer(this));
        this.players.add(new BlackPlayer(this));
        this.currentPlayer = 0;
        setUp();
    }

    /**
     * Sets up the board with the default positions of the peices as defined in the player subclasses
     */
    public void setUp(){
        for(Player player : players){
            player.setUp();
        }
    }

    /**
     * Clears the board of all pieces
     */
    public void tearDown(){
        for(Player player : players){
            player.tearDown();
        }
    }

    /**
     * Provides the current player number
     * @return the current player number
     */
    public int getCurrentPlayerNum(){
        return currentPlayer;
    }

    /**
     * Returns the current player object
     * @return player object of current player
     */
    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    /**
     * Returns player object for a given player number based on the position of the array list
     * @param playerNum - Player index in array list
     * @return Player object
     */
    public Player getPlayer(int playerNum){
        return players.get(playerNum);
    }

    /**
     * Attempts to move the peice specified at the pieceLoc param to the destination,
     * will throw an error if the movement is not possible
     * @param pieceLoc - location of the piece to be located
     * @param destLoc - location of the destination
     * @throws IllegalMoveException
     */
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

    /**
     * Overloaded version that takes a piece reference. Attempts to move the peice to the dest
     * will throw an error if the movement is not possible
     * @param piece - reference of the piece to be moved
     * @param destLoc - location of the destination
     * @throws IllegalMoveException
     */
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
        if(piece.getPlayer() == getCurrentPlayer() && !isCurrentPlayerInCheck()){
            return piece.canMoveTo(destLoc);
        }
        return false;
    }

    public boolean canMovePiece(GamePiece piece, Location destLoc){
        if(piece.getPlayer() == getCurrentPlayer() && !isCurrentPlayerInCheck()){
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

    public boolean isCurrentPlayerInCheck(){
        for(Player player : players){
            if(player != getCurrentPlayer()){
                if(getCurrentPlayer().isInCheckFrom(player)){
                    return true;
                }
            }
        }
        return false;
    }


}
