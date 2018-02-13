public class Rook extends GamePiece {

    public Rook(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        this.pieceString = "Rook";
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
        int xSign = (int) Math.signum(xDist);
        int yDist = dest.getY() - loc.getY();
        int ySign = (int) Math.signum(yDist);
        //Check for collision before hand
        for(int i = 0; i <= Math.abs(xDist); i++){
            for(int j = 0; j <= Math.abs(yDist); j++){
                if(i==0 && j==0){
                    continue;
                }
                int tempX = loc.getX()+i*xSign;
                int tempY = loc.getY()+j*ySign;
                if(!board.isLocEmpty(new Location(tempX, tempY)) && !(tempX == dest.getX() && tempY == dest.getY())){
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
