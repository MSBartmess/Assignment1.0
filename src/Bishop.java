/**
 * Bishop Class:
 * Allows for movement in diagonal directions only
 */
public class Bishop extends GamePiece {

    public Bishop(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        this.pieceString = "Bishop";
    }

    @Override
    public boolean canMoveTo(Location dest){
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        //Check Diagonal
        int xDist = dest.getX() - loc.getX();
        int xSign = (int) Math.signum(xDist);
        int yDist = dest.getY() - loc.getY();
        int ySign = (int) Math.signum(yDist);
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
}
