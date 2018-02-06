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



}
