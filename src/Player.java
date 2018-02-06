import java.util.ArrayList;

public class Player {
    protected ChessBoard board;
    protected ArrayList<GamePiece> pieces;
    protected int playerDirectionModifier;
    protected King kingPiece;

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

    public ArrayList<GamePiece> getPieces() {
        return pieces;
    }

    public void setUp(){

    }
    public void tearDown(){
        pieces.clear();
    }
    public boolean isInCheckFrom(Player opponent){
        for(GamePiece piece : opponent.getPieces()){
            if(piece.canCapture(kingPiece.getLoc())){
                return true;
            }
        }
        return false;
    }
}

