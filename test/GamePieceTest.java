import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GamePieceTest {
    private ChessBoard board;
    private Player player0;
    private Player player1;
    @Before
    public void setUp(){
        board = new ChessBoard();
        board.tearDown();
        player0 = board.getPlayer(0);
        player1 = board.getPlayer(1);
    }

    @Test
    public void pawnIsAtTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(4,4));
        assertTrue(testPawn.isAt(new Location(4,4)));
    }
    @Test
    public void pawnGetLocTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(6,2));
        assertEquals(new Location(6,2), testPawn.getLoc());
    }
    @Test
    public void pawnPlayer0CanMoveTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(6,2));
        assertTrue(testPawn.canMoveTo(new Location(6,3)));
        assertFalse(testPawn.canMoveTo(new Location(7,3)));
    }
    @Test
    public void pawnPlayer1CanMoveTest(){
        Pawn testPawn = new Pawn(board, player1, new Location(4,5));
        assertTrue(testPawn.canMoveTo(new Location(4,4)));
        assertFalse(testPawn.canMoveTo(new Location(4,6)));
    }
    @Test
    public void pawnPlayer0MoveTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(6,2));
        player0.addPiece(testPawn);
        try {
            testPawn.moveTo(new Location(6, 3));
            assertEquals(testPawn, board.pieceAt(new Location(6, 3)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }
    @Test
    public void pawnPlayer1MoveTest(){
        Pawn testPawn = new Pawn(board, player1, new Location(4,5));
        player1.addPiece(testPawn);
        try{
            testPawn.moveTo(new Location(4,4));
            assertEquals(testPawn, board.pieceAt(new Location(4,4)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }




}
