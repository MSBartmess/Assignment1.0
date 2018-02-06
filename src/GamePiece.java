public abstract class GamePiece {
    protected Location loc;
    protected ChessBoard board;
    protected Player player;

    abstract boolean canMoveTo(Location dest);
    abstract void moveTo(Location dest) throws IllegalMoveException;

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
    public boolean isOtherPlayerPiece(Location checkLoc){
         return (!board.isLocEmpty(checkLoc)) && !(board.pieceAt(checkLoc).getPlayer().equals(this.player));
    }
}

class Pawn extends GamePiece {
    private boolean isFirstMove;

    public Pawn(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        isFirstMove = true;
    }

    @Override
    public boolean canMoveTo(Location dest){
        if(dest == null || !dest.isValidLocation(this.board)){
            return false;
        }
        //Moving forward two spaces

        if(isFirstMove && dest.getX() == loc.getX() && (dest.getY() == loc.getY() + (2 * this.player.getDirModifier()))  && board.isLocEmpty(dest)){
            return true;
        }
        //Moving forward 1 space
        if(dest.getX() == loc.getX() && dest.getY() == (loc.getY() + this.player.getDirModifier()) && board.isLocEmpty(dest)){
            return true;
        }
        //Pawns can move diagonally to take a peice
        if(dest.getY() == (loc.getY() + this.player.getDirModifier()) &&
                ((dest.getX() == (this.loc.getX() + 1)) || (dest.getX() == (this.loc.getX() - 1))) && isOtherPlayerPiece(dest)){
                return true;
        }

        return false;
    }

    @Override
    public void moveTo(Location dest) throws IllegalMoveException {
        if(canMoveTo(dest)){
            //Handle Capture
            if(isOtherPlayerPiece(dest)){
                GamePiece takenPiece = board.pieceAt(dest);
                takenPiece.getPlayer().removePiece(takenPiece);
            }
            this.loc.setLoc(dest);
        }
        else{
            throw new IllegalMoveException("Pawn can not be moved to location");
        }
    }
}

class Rook extends GamePiece {

    public Rook(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }

}

class Knight extends GamePiece {

    public Knight(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }
}


class Bishop extends GamePiece {
    public Bishop(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }
}

class King extends GamePiece {
    public King(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }
}

class Queen extends GamePiece {
    public Queen(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }
}