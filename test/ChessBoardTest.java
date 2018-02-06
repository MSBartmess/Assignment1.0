import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ChessBoardTest {



    @Test
    public void setUpTest(){
        ChessBoard board = new ChessBoard();
        //assertThat(board.pieceAt(new Location(0,0)), isA(Rook.class));
        //assertThat(board.pieceAt(new Location(0,1)), isA(Pawn.class));
        //assertThat(board.pieceAt(new Location(7,7)), isA(Rook.class));
        //assertThat(board.pieceAt(new Location(7,6)), isA(Pawn.class));
    }


    @Test
    public void canMovePieceAtLocToLocTest(){
        ChessBoard board = new ChessBoard();
        assertTrue(board.canMovePiece(new Location(1,0), new Location(0,2)));
    }

    @Test
    public void canMovePieceReferenceToLocTest(){
        ChessBoard board = new ChessBoard();
        GamePiece testPawn = board.pieceAt(new Location(3,1));
        assertTrue(board.canMovePiece(testPawn, new Location(3,3)));
    }

    @Test
    public void movePieceAtLocToLocTest(){
        ChessBoard board = new ChessBoard();
        GamePiece testPawn = board.pieceAt(new Location(0,1));
        try {
            board.movePiece(new Location(0,1), new Location(0,2));
        } catch (IllegalMoveException e) {
            fail("Move was declared illegal");
        }
        assertEquals(testPawn, board.pieceAt(new Location(0,2)));
    }

    @Test
    public void movePieceReferenceToLocTest(){
        ChessBoard board = new ChessBoard();
        GamePiece testPawn = board.pieceAt(new Location(0,1));
        try {
            board.movePiece(testPawn, new Location(0,2));
        } catch (IllegalMoveException e) {
            fail("Move was declared illegal");
        }
        assertEquals(testPawn, board.pieceAt(new Location(0,2)));
    }

    @Test
    public void getCurrentPlayerTest(){
        ChessBoard board = new ChessBoard();
        assertEquals(0,board.getCurrentPlayerNum());
    }

    @Test
    public void getSecondCurrentPlayerTest(){
        ChessBoard board = new ChessBoard();
        try {
            board.movePiece(new Location(0,1),new Location(0,2));
        } catch (IllegalMoveException e) {
            fail("Move was declared illegal");
        }
        assertEquals(1,board.getCurrentPlayerNum());
    }

    @Test
    public void currentPlayerLoopingTest(){
        ChessBoard board = new ChessBoard();
        try {
            board.movePiece(new Location(0,1),new Location(0,2));
            board.movePiece(new Location(7,7),new Location(7,6));
        } catch (IllegalMoveException e) {
            fail("Move was declared illegal");
        }

        assertEquals(0,board.getCurrentPlayerNum());
    }
}
