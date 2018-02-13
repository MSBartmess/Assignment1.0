/**
 * King Class: Can move only one space in a turn
 */
public class King extends GamePiece {

    public King(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        this.pieceString = "King";
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

}

