public class WhitePlayer extends Player{
    public WhitePlayer(ChessBoard board){
        super(board);
        playerDirectionModifier = 1;
    }

    @Override
    public void setUp() {
        super.setUp();
        for(int i = 0; i<8; i++){
            pieces.add(new Pawn(this.board,this, new Location(i,1)));
        }
        pieces.add(new Rook(this.board,this, new Location(0,0)));
        pieces.add(new Rook(this.board,this, new Location(7,0)));
        pieces.add(new Knight(this.board, this, new Location(1,0)));
        pieces.add(new Knight(this.board, this, new Location(6,0)));
        pieces.add(new Bishop(this.board, this, new Location(2,0)));
        pieces.add(new Bishop(this.board, this, new Location(5,0)));
        pieces.add(new King(this.board, this, new Location(4,0)));
        pieces.add(new Queen(this.board, this, new Location(3,0)));
    }
}
