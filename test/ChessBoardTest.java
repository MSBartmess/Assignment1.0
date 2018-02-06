import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ChessBoardTest {



    @Test
    public void setUpTest(){
        ChessBoard board = new ChessBoard();
        //assertThat(board.pieceAt(new Location(0,0)), isA(Rook.class));
        //assertThat(board.pieceAt(new Location(0,1)), isA(Pawn.class));
        //assertThat(board.pieceAt(new Location(8,8)), isA(Rook.class));
        //assertThat(board.pieceAt(new Location(8,7)), isA(Pawn.class));
    }


    @Test
    public void canMovePieceAtLocToLocTest(){
        ChessBoard board = new ChessBoard();
        assertTrue(board.canMovePiece(new Location(2,0), new Location(1,2)));
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
        board.movePiece(new Location(0,1), new Location(0,2));
        assertEquals(testPawn, board.pieceAt(new Location(0,2)));
    }

    @Test
    public void movePieceReferenceToLocTest(){
        ChessBoard board = new ChessBoard();
        GamePiece testPawn = board.pieceAt(new Location(0,1));
        board.movePiece(testPawn, new Location(0,2));
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
        board.movePiece(new Location(0,1),new Location(0,2));
        assertEquals(1,board.getCurrentPlayerNum());
    }

    @Test
    public void currentPlayerLoopingTest(){
        ChessBoard board = new ChessBoard();
        board.movePiece(new Location(0,1),new Location(0,2));
        board.movePiece(new Location(7,7),new Location(7,6));
        assertEquals(0,board.getCurrentPlayerNum());
    }
}
