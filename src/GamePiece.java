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
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
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
            isFirstMove = false;
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
        //Check if destination is valid
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        //Check if destination is on the same row
        if(dest.getX() != loc.getX() && dest.getY() != loc.getY()){
            return false;
        }
        int xDist = dest.getX() - loc.getX();
        int xSign = xDist > 0 ? 1 : -1;
        int yDist = dest.getY() - loc.getY();
        int ySign = yDist > 0 ? 1 : -1;
        //Check for collision before hand
        for(int i = 1; i <= Math.abs(xDist); i++){
            for(int j = 1; j <= Math.abs(yDist); j++){
                int tempX = loc.getX()+i*xSign;
                int tempY = loc.getY()+j*ySign;
                if(!board.isLocEmpty(new Location(tempX, tempY)) && (tempX != dest.getX() && tempY != dest.getY())){
                    return false;
                }
            }
        }
        if(!board.isLocEmpty(dest) && !isOtherPlayerPiece(dest)){
            return false;
        }
        return true;
    }

    @Override
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
            throw new IllegalMoveException("Rook can not be moved to location");
        }
    }

}

class Knight extends GamePiece{

    public Knight(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
    }

    @Override
    public boolean canMoveTo(Location dest){
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        if(dest.getX() == loc.getX() + 2 || dest.getX() == loc.getX() - 2){
            if(dest.getY() == loc.getY() + 1 || dest.getY() == loc.getY() - 1){
                return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
            }
        }
        if(dest.getY() == loc.getY() + 2 || dest.getY() == loc.getY() - 2){
            if(dest.getX() == loc.getX() + 1 || dest.getX() == loc.getX() - 1){
                return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
            }
        }
        return false;
    }

    @Override
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
            throw new IllegalMoveException("Knight can not be moved to location");
        }
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
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        //Check Diagonal
        int xDist = dest.getX() - loc.getX();
        int xSign = xDist > 0 ? 1 : -1;
        int yDist = dest.getY() - loc.getY();
        int ySign = yDist > 0 ? 1 : -1;
        if(Math.abs(xDist)!=Math.abs(yDist)){
            return false;
        }
        //Check for collision
        for(int i = 1; i<Math.abs(xDist)-1; i++){
            int tempX = loc.getX() + i*xSign;
            int tempY = loc.getY() + i*ySign;
            if(!board.isLocEmpty(new Location(tempX, tempY))){
                return false;
            }
        }
        return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
    }

    @Override
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
            throw new IllegalMoveException("Bishop can not be moved to location");
        }
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
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        if(Math.abs(dest.getX()-loc.getX())<=1 && Math.abs(dest.getY()-loc.getY())<=1){
            return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
        }
        return false;
    }

    @Override
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
            throw new IllegalMoveException("King can not be moved to location");
        }
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
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)) {
            return false;
        }
        return isLegalDiagonal(dest) || isLegalInLine(dest);
    }

    @Override
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
            throw new IllegalMoveException("Queen can not be moved to location");
        }
    }
    private boolean isLegalDiagonal(Location dest){
        //Check Diagonal
        int xDist = dest.getX() - loc.getX();
        int xSign = xDist > 0 ? 1 : -1;
        int yDist = dest.getY() - loc.getY();
        int ySign = yDist > 0 ? 1 : -1;
        if(Math.abs(xDist)!=Math.abs(yDist)){
            return false;
        }
        //Check for collision
        for(int i = 1; i<Math.abs(xDist)-1; i++){
            int tempX = loc.getX() + i*xSign;
            int tempY = loc.getY() + i*ySign;
            if(!board.isLocEmpty(new Location(tempX, tempY))){
                return false;
            }
        }
        return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
    }
    private boolean isLegalInLine(Location dest){
        //Check if destination is on the same row
        if(dest.getX() != loc.getX() && dest.getY() != loc.getY()){
            return false;
        }
        int xDist = dest.getX() - loc.getX();
        int xSign = xDist > 0 ? 1 : -1;
        int yDist = dest.getY() - loc.getY();
        int ySign = yDist > 0 ? 1 : -1;
        //Check for collision before hand
        for(int i = 1; i <= Math.abs(xDist); i++){
            for(int j = 1; j <= Math.abs(yDist); j++){
                int tempX = loc.getX()+i*xSign;
                int tempY = loc.getY()+j*ySign;
                if(!board.isLocEmpty(new Location(tempX, tempY)) && (tempX != dest.getX() && tempY != dest.getY())){
                    return false;
                }
            }
        }
        if(!board.isLocEmpty(dest) && !isOtherPlayerPiece(dest)){
            return false;
        }
        return true;
    }
}