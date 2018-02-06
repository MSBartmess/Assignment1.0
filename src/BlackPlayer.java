public class BlackPlayer extends Player{
    public BlackPlayer(ChessBoard board){
        super(board);
        playerDirectionModifier = -1;
    }
    @Override
    public void setUp() {
        super.setUp();
        for(int i = 0; i<8; i++){
            pieces.add(new Pawn(this.board,this, new Location(i,6)));
        }
        pieces.add(new Rook(this.board,this, new Location(0,7)));
        pieces.add(new Rook(this.board,this, new Location(7,7)));
        pieces.add(new Knight(this.board, this, new Location(1,7)));
        pieces.add(new Knight(this.board, this, new Location(6,7)));
        pieces.add(new Bishop(this.board, this, new Location(2,7)));
        pieces.add(new Bishop(this.board, this, new Location(5,7)));
        pieces.add(new King(this.board, this, new Location(4,7)));
        pieces.add(new Queen(this.board, this, new Location(3,7)));
    }
}
