public class Knight extends GamePiece{

    public Knight(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        this.pieceString = "Knight";
    }

    @Override
    public boolean canMoveTo(Location dest){
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        //If moving 2 in the x direction, then you can only move 1 in the Y direction
        if(dest.getX() == loc.getX() + 2 || dest.getX() == loc.getX() - 2){
            if(dest.getY() == loc.getY() + 1 || dest.getY() == loc.getY() - 1){
                return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
            }
        }
        //If moving 2 in the y direction, then you can only move 1 in the Y direction
        if(dest.getY() == loc.getY() + 2 || dest.getY() == loc.getY() - 2){
            if(dest.getX() == loc.getX() + 1 || dest.getX() == loc.getX() - 1){
                return isOtherPlayerPiece(dest) || board.isLocEmpty(dest);
            }
        }

        return false;
    }


}

