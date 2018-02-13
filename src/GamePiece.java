/**
 * GamePiece abstract class, subclasses implement the movement methods
 */
public abstract class GamePiece {
    protected Location loc;
    protected ChessBoard board;
    protected Player player;
    protected String pieceString;
    abstract boolean canMoveTo(Location dest);

    void moveTo(Location dest) throws IllegalMoveException{
        if(canMoveTo(dest)){
            //Handle Capture
            if(isOtherPlayerPiece(dest)){
                GamePiece takenPiece = board.pieceAt(dest);
                takenPiece.getPlayer().removePiece(takenPiece);
            }
            this.loc.setLoc(dest);
        }
        else{
            throw new IllegalMoveException(pieceString + " can not be moved to location");
        }
    }

    public boolean equals(GamePiece piece){
        return board == piece.getBoard() && player == piece.getPlayer() && loc.equals(piece.loc);
    }

    public Location getLoc(){
        return loc;
    }

    public boolean isAt(Location checkLoc){
        return loc.equals(checkLoc);
    }

    public Player getPlayer(){
        return player;
    }

    public ChessBoard getBoard(){
        return board;
    }

    public boolean canCapture(Location capLoc){
        return canMoveTo(capLoc);
    }

    public boolean isOtherPlayerPiece(Location checkLoc){
         return (!board.isLocEmpty(checkLoc)) && !(board.pieceAt(checkLoc).getPlayer().equals(this.player));
    }
}

