import java.util.ArrayList;

public class Player {
    protected ChessBoard board;
    protected ArrayList<GamePiece> pieces;
    protected int playerDirectionModifier;

    public Player(ChessBoard board){
        this.board = board;
        this.pieces = new ArrayList<>();
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

    public void removePiece(GamePiece piece){
        pieces.remove(piece);
    }
    public int getRemainingPiecesNum(){
        return pieces.size();
    }
    public void addPiece(GamePiece piece){
        pieces.add(piece);
    }

    public void setUp(){

    }
    public void tearDown(){
        pieces.clear();
    }
}

