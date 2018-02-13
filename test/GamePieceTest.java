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
    public void isAtTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(4,4));
        assertTrue(testPawn.isAt(new Location(4,4)));
    }
    @Test
    public void getLocTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(6,2));
        assertEquals(new Location(6,2), testPawn.getLoc());
    }
    @Test
    public void pawnPlayer0CanMoveTest(){
        Pawn testPawn = new Pawn(board, player0, new Location(6,2));
        assertTrue(testPawn.canMoveTo(new Location(6,3)));
        assertFalse(testPawn.canMoveTo(new Location(7,3)));
        assertFalse(testPawn.canMoveTo(new Location(6,2)));
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

    @Test
    public void pawnPlayerCanCaptureTest(){

    }
    //Rook move tests
    @Test
    public void rookCanMoveTestFree(){
        Rook testRook = new Rook(board, player0, new Location(6,2));
        assertTrue(testRook.canMoveTo(new Location(6,7)));
        assertTrue(testRook.canMoveTo(new Location(6,1)));
        assertTrue(testRook.canMoveTo(new Location(2,2)));
        assertTrue(testRook.canMoveTo(new Location(7,2)));
        assertFalse(testRook.canMoveTo(new Location(7,3)));
        assertFalse(testRook.canMoveTo(new Location(2,5)));
        assertFalse(testRook.canMoveTo(new Location(6,2)));
    }

    @Test
    public void rookCanMoveTestCaptures(){
        Rook testRook = new Rook(board, player0, new Location(6,2));
        player0.addPiece(testRook);
        Pawn testPawn0 = new Pawn(board,player1, new Location(0,2));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player1, new Location(6,5));
        player1.addPiece(testPawn1);
        assertTrue(testRook.canMoveTo(new Location(0,2)));
        assertTrue(testRook.canMoveTo(new Location(6,5)));
    }
    @Test
    public void rookCanMoveTestCollisions(){
        Rook testRook = new Rook(board, player0, new Location(6,2));
        player0.addPiece(testRook);
        Pawn testPawn0 = new Pawn(board,player0, new Location(2,2));
        player0.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player0, new Location(6,5));
        player0.addPiece(testPawn1);
        assertFalse(testRook.canMoveTo(new Location(1,2)));
        assertFalse(testRook.canMoveTo(new Location(6,5)));
    }

    @Test
    public void rookMoveTest(){
        Rook testRook = new Rook(board, player1, new Location(4,5));
        player0.addPiece(testRook);
        try{
            testRook.moveTo(new Location(4,2));
            assertEquals(testRook, board.pieceAt(new Location(4,2)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    @Test
    public void rookMoveCaptureTest(){
        Rook testRook0 = new Rook(board, player0, new Location(4,5));
        player0.addPiece(testRook0);
        Rook testRook1 = new Rook(board, player1, new Location(4,2));
        player1.addPiece(testRook1);
        try{
            testRook0.moveTo(new Location(4,2));
            assertEquals(testRook0, board.pieceAt(new Location(4,2)));
            assertEquals(0, board.getPlayer(1).getRemainingPiecesNum());
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }


    //Knight move tests
    @Test
    public void knightCanMoveTestFree(){
        Knight testKnight = new Knight(board, player0, new Location(4,4));
        assertTrue(testKnight.canMoveTo(new Location(6,3)));
        assertTrue(testKnight.canMoveTo(new Location(6,5)));
        assertTrue(testKnight.canMoveTo(new Location(2,3)));
        assertTrue(testKnight.canMoveTo(new Location(2,5)));
        assertTrue(testKnight.canMoveTo(new Location(3,6)));
        assertTrue(testKnight.canMoveTo(new Location(3,2)));
        assertTrue(testKnight.canMoveTo(new Location(5,6)));
        assertTrue(testKnight.canMoveTo(new Location(5,2)));
        assertFalse(testKnight.canMoveTo(new Location(7,3)));
        assertFalse(testKnight.canMoveTo(new Location(4,6)));
    }

    @Test
    public void knightCanMoveTestCaptures(){
        Knight testKnight = new Knight(board, player0, new Location(4,4));
        player0.addPiece(testKnight);
        Pawn testPawn0 = new Pawn(board,player1, new Location(6,3));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player1, new Location(5,2));
        player1.addPiece(testPawn1);
        assertTrue(testKnight.canMoveTo(new Location(6,3)));
        assertTrue(testKnight.canMoveTo(new Location(5,2)));
    }
    @Test
    public void knightCanMoveTestCollisions(){
        Knight testKnight = new Knight(board, player0, new Location(4,4));
        player0.addPiece(testKnight);
        Pawn testPawn0 = new Pawn(board,player1, new Location(4,5));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player0, new Location(6,5));
        player0.addPiece(testPawn1);
        assertTrue(testKnight.canMoveTo(new Location(3,6)));
        assertFalse(testKnight.canMoveTo(new Location(6,5)));
    }

    @Test
    public void knightMoveTest(){
        Knight testKnight = new Knight(board, player1, new Location(4,4));
        player0.addPiece(testKnight);
        try{
            testKnight.moveTo(new Location(6,5));
            assertEquals(testKnight, board.pieceAt(new Location(6,5)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    @Test
    public void knightMoveCaptureTest(){
        Knight testKnight0 = new Knight(board, player0, new Location(4,4));
        player0.addPiece(testKnight0);
        Knight testKnight1 = new Knight(board, player1, new Location(3,6));
        player1.addPiece(testKnight1);
        try{
            testKnight0.moveTo(new Location(3,6));
            assertEquals(testKnight0, board.pieceAt(new Location(3,6)));
            assertEquals(0, board.getPlayer(1).getRemainingPiecesNum());
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    //Bishop move tests
    @Test
    public void bishopCanMoveTestFree(){
        Bishop testBishop = new Bishop(board, player0, new Location(4,4));
        assertTrue(testBishop.canMoveTo(new Location(7,7)));
        assertTrue(testBishop.canMoveTo(new Location(2,2)));
        assertTrue(testBishop.canMoveTo(new Location(7,1)));
        assertTrue(testBishop.canMoveTo(new Location(2,6)));
        assertFalse(testBishop.canMoveTo(new Location(7,3)));
        assertFalse(testBishop.canMoveTo(new Location(4,6)));
    }

    @Test
    public void bishopCanMoveTestCaptures(){
        Bishop testBishop = new Bishop(board, player0, new Location(4,4));
        player0.addPiece(testBishop);
        Pawn testPawn0 = new Pawn(board,player1, new Location(7,7));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player1, new Location(2,6));
        player1.addPiece(testPawn1);
        assertTrue(testBishop.canMoveTo(new Location(7,7)));
        assertTrue(testBishop.canMoveTo(new Location(2,6)));
    }
    @Test
    public void bishopCanMoveTestCollisions(){
        Bishop testBishop = new Bishop(board, player0, new Location(4,4));
        player0.addPiece(testBishop);
        Pawn testPawn0 = new Pawn(board,player1, new Location(6,6));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player0, new Location(2,6));
        player0.addPiece(testPawn1);
        assertTrue(testBishop.canMoveTo(new Location(7,7)));
        assertFalse(testBishop.canMoveTo(new Location(2,6)));
    }

    @Test
    public void bishopMoveTest(){
        Bishop testBishop = new Bishop(board, player1, new Location(4,4));
        player0.addPiece(testBishop);
        try{
            testBishop.moveTo(new Location(1,7));
            assertEquals(testBishop, board.pieceAt(new Location(1,7)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    @Test
    public void bishopMoveCaptureTest(){
        Bishop testBishop0 = new Bishop(board, player0, new Location(4,4));
        player0.addPiece(testBishop0);
        Bishop testBishop1 = new Bishop(board, player1, new Location(3,5));
        player1.addPiece(testBishop1);
        try{
            testBishop0.moveTo(new Location(3,5));
            assertEquals(testBishop0, board.pieceAt(new Location(3,5)));
            assertEquals(0, board.getPlayer(1).getRemainingPiecesNum());
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    //King move tests
    @Test
    public void kingCanMoveTestFree(){
        King testKing = new King(board, player0, new Location(4,4));
        assertTrue(testKing.canMoveTo(new Location(4,5)));
        assertTrue(testKing.canMoveTo(new Location(4,3)));
        assertTrue(testKing.canMoveTo(new Location(3,4)));
        assertTrue(testKing.canMoveTo(new Location(5,3)));
        assertTrue(testKing.canMoveTo(new Location(5,5)));
        assertTrue(testKing.canMoveTo(new Location(5,3)));
        assertTrue(testKing.canMoveTo(new Location(3,5)));
        assertTrue(testKing.canMoveTo(new Location(3,3)));
        assertFalse(testKing.canMoveTo(new Location(4,4)));
        assertFalse(testKing.canMoveTo(new Location(7,3)));
        assertFalse(testKing.canMoveTo(new Location(4,6)));
    }

    @Test
    public void kingCanMoveTestCaptures(){
        King testKing = new King(board, player0, new Location(4,4));
        player0.addPiece(testKing);
        Pawn testPawn0 = new Pawn(board,player1, new Location(4,5));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player1, new Location(3,3));
        player1.addPiece(testPawn1);
        assertTrue(testKing.canMoveTo(new Location(4,5)));
        assertTrue(testKing.canMoveTo(new Location(3,3)));
    }
    @Test
    public void kingCanMoveTestCollisions(){
        King testKing = new King(board, player0, new Location(4,4));
        player0.addPiece(testKing);
        Pawn testPawn1 = new Pawn(board,player0, new Location(4,5));
        player0.addPiece(testPawn1);
        assertFalse(testKing.canMoveTo(new Location(4,5)));
    }

    @Test
    public void kingMoveTest(){
        King testKing = new King(board, player1, new Location(4,4));
        player0.addPiece(testKing);
        try{
            testKing.moveTo(new Location(3,5));
            assertEquals(testKing, board.pieceAt(new Location(3,5)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    @Test
    public void kingMoveCaptureTest(){
        King testKing0 = new King(board, player0, new Location(4,4));
        player0.addPiece(testKing0);
        Pawn testPawn1 = new Pawn(board, player1, new Location(3,4));
        player1.addPiece(testPawn1);
        try{
            testKing0.moveTo(new Location(3,4));
            assertEquals(testKing0, board.pieceAt(new Location(3,4)));
            assertEquals(0, board.getPlayer(1).getRemainingPiecesNum());
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    //Queen move tests
    @Test
    public void queenCanMoveTestFree(){
        Queen testQueen = new Queen(board, player0, new Location(4,4));
        assertTrue(testQueen.canMoveTo(new Location(7,7)));
        assertTrue(testQueen.canMoveTo(new Location(2,2)));
        assertTrue(testQueen.canMoveTo(new Location(7,1)));
        assertTrue(testQueen.canMoveTo(new Location(2,6)));
        assertTrue(testQueen.canMoveTo(new Location(4,6)));
        assertTrue(testQueen.canMoveTo(new Location(6,4)));
        assertFalse(testQueen.canMoveTo(new Location(7,3)));
        assertFalse(testQueen.canMoveTo(new Location(5,6)));
    }

    @Test
    public void queenCanMoveTestCaptures(){
        Queen testQueen = new Queen(board, player0, new Location(4,4));
        player0.addPiece(testQueen);
        Pawn testPawn0 = new Pawn(board,player1, new Location(7,7));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player1, new Location(2,6));
        player1.addPiece(testPawn1);
        assertTrue(testQueen.canMoveTo(new Location(7,7)));
        assertTrue(testQueen.canMoveTo(new Location(2,6)));
    }
    @Test
    public void queenCanMoveTestCollisions(){
        Queen testQueen = new Queen(board, player0, new Location(4,4));
        player0.addPiece(testQueen);
        Pawn testPawn0 = new Pawn(board,player1, new Location(6,6));
        player1.addPiece(testPawn0);
        Pawn testPawn1 = new Pawn(board,player0, new Location(2,6));
        player0.addPiece(testPawn1);
        assertTrue(testQueen.canMoveTo(new Location(7,7)));
        assertFalse(testQueen.canMoveTo(new Location(2,6)));
    }

    @Test
    public void queenMoveTest(){
        Queen testQueen = new Queen(board, player1, new Location(4,4));
        player0.addPiece(testQueen);
        try{
            testQueen.moveTo(new Location(1,7));
            assertEquals(testQueen, board.pieceAt(new Location(1,7)));
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }

    @Test
    public void queenInvalidMoveTest(){
        Queen testQueen = new Queen(board, player1, new Location(4,4));
        player0.addPiece(testQueen);
        try{
            testQueen.moveTo(new Location(10,7));
            fail("Move should not have bee valid");
        } catch (IllegalMoveException e) {
            assertEquals(e.getMessage(), "Queen can not be moved to location");
        }
    }


    @Test
    public void queenMoveCaptureTest(){
        Queen testQueen0 = new Queen(board, player0, new Location(4,4));
        player0.addPiece(testQueen0);
        Queen testQueen1 = new Queen(board, player1, new Location(3,5));
        player1.addPiece(testQueen1);
        try{
            testQueen0.moveTo(new Location(3,5));
            assertEquals(testQueen0, board.pieceAt(new Location(3,5)));
            assertEquals(0, board.getPlayer(1).getRemainingPiecesNum());
        } catch (IllegalMoveException e) {
            fail("Move was not declared valid");
        }
    }
}
