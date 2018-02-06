import java.util.ArrayList;

public class Player {
    private ChessBoard board;
    private ArrayList<GamePiece> pieces;
    private int playerDirectionModifier;

    public Player(ChessBoard board, int playerDirection){
        this.board = board;
        this.pieces = new ArrayList<>();
        this.playerDirectionModifier = playerDirection;
    }

    public int getDirModifier(){
        return  playerDirectionModifier;
    }

    public GamePiece pieceAt(Location loc){
        for(GamePiece piece : pieces){
            if(piece.isAt(loc)){
                return piece;
            }
        }
        return null;
    }

    public void removePieceAt(Location loc){

    }

    public void removePiece(GamePiece piece){

    }
    public void addPiece(GamePiece piece){
        pieces.add(piece);
    }

}
