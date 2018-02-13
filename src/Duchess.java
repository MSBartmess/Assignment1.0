/**
 * Duchess Class: Can move in the same directions as queen, but only 3 spaces
 */
public class Duchess extends GamePiece {
    private static int MAXDUCHESSDISTANCE = 3;
    public Duchess(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        this.pieceString = "Duchess";
    }

    @Override
    public boolean canMoveTo(Location dest){
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)) {
            return false;
        }
        return isLegalDiagonal(dest) || isLegalInLine(dest);
    }

    private boolean isLegalDiagonal(Location dest){
        //Check Diagonal
        int xDist = dest.getX() - loc.getX();
        int xSign = (int) Math.signum(xDist);
        int yDist = dest.getY() - loc.getY();
        int ySign = (int) Math.signum(yDist);
        if(Math.abs(xDist)!=Math.abs(yDist)){
            return false;
        }
        if(Math.abs(xDist) > MAXDUCHESSDISTANCE || Math.abs(yDist) > MAXDUCHESSDISTANCE){
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
        int xSign = (int) Math.signum(xDist);
        int yDist = dest.getY() - loc.getY();
        int ySign = (int) Math.signum(yDist);
        if(Math.abs(xDist) > MAXDUCHESSDISTANCE || Math.abs(yDist) > MAXDUCHESSDISTANCE){
            return false;
        }
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
