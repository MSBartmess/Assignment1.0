/**
 * Pawn Class: Can move one space forward, except on the first move, where it can move two spaces
 */
public class Pawn extends GamePiece {
    private boolean isFirstMove;
    public Pawn(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        this.pieceString = "Pawn";
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
    public boolean canCapture(Location dest){
        if(dest == null || !dest.isValidLocation(this.board) || loc.equals(dest)){
            return false;
        }
        //Pawns can move diagonally to take a peice
        if(dest.getY() == (loc.getY() + this.player.getDirModifier()) &&
                ((dest.getX() == (this.loc.getX() + 1)) || (dest.getX() == (this.loc.getX() - 1))) && isOtherPlayerPiece(dest)){
            return true;
        }

        return false;
    }

}
