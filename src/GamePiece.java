public abstract class GamePiece {
    protected Location loc;
    protected ChessBoard board;
    protected Player player;

    abstract boolean canMoveTo(Location dest);
    abstract void moveTo(Location dest);
    abstract boolean equals(GamePiece piece);
    abstract Location getLoc();
    abstract boolean isAt(Location checkLoc);
}

class Pawn extends GamePiece {
    private boolean isFirstMove;

    public Pawn(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        isFirstMove = true;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }

    @Override
    boolean equals(GamePiece piece) {
        return false;
    }

    @Override
    Location getLoc() {
        return null;
    }

    @Override
    boolean isAt(Location checkLoc) {
        return false;
    }
}

class Rook extends GamePiece {
    private boolean isFirstMove;

    public Rook(ChessBoard board, Player player, Location loc){
        this.board = board;
        this.player = player;
        this.loc = loc;
        isFirstMove = true;
    }

    @Override
    public boolean canMoveTo(Location dest){
        return false;
    }

    @Override
    void moveTo(Location dest) {

    }

    @Override
    boolean equals(GamePiece piece) {
        return false;
    }

    @Override
    Location getLoc() {
        return null;
    }

    @Override
    boolean isAt(Location checkLoc) {
        return false;
    }
}