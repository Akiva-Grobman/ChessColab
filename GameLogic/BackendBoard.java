package GameLogic;

import GUI.GUIBoard;
import GameObjects.*;

import java.awt.*;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackendBoard {

    private Tile [][] board;
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    public Piece getPiece(Point position) throws NullPointerException {
        return board[position.y][position.x].getPiece();
    }

    public enum Type {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    public BackendBoard(){
        initializeBoard();
    }




    private void initializeBoard() {
        board = new Tile[ROWS][COLUMNS];
        Color color;
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if((x + y) % 2 == 0) {
                    color = Color.white;
                } else {
                    color = Color.black;
                }
                board[y][x] = new Tile(x, y, color);
            }
        }
        addPieces();
    }

    private void addPieces() {
        Color color;
        for (int y = 0; y < ROWS; y++)
        {
            for (int x = 0; x < COLUMNS; x++)
            {
                color = getPieceColor(y);
                if(y == 0 || y == 7)
                {
                    switch(x)
                    {
                        case 0:
                        case 7:
                        {
                            board[y][x].addPiece(new Rook(color));
                            break;
                        }
                        case 1:
                        case 6:
                        {
                            board[y][x].addPiece(new Knight(color));
                            break;
                        }
                        case 2:
                        case 5:
                        {
                            board[y][x].addPiece(new Bishop(color));
                            break;
                        }
                    }
                }
                if(y == 1 || y == 6)
                {
                    board[y][x].addPiece(new Pawn(color));
                }
            }
        }
        // black king
        board[0][4].addPiece(new King(Color.black));
        // black queen
        board[0][3].addPiece(new Queen(Color.black));
        // white king
        board[7][4].addPiece(new King(Color.white));
        // white queen
        board[7][3].addPiece(new Queen(Color.white));
    }

    private Color getPieceColor(int row){
        if(row == 0 || row == 1){
            return Color.black;
        } else {
            return Color.white;
        }
    }

    public boolean getHasPiece(Point position) {
        return board[position.y][position.x].isHasPiece();
    }

    public void makeAMove(Point origin, Point destination) {
        board[origin.y][origin.x].getPiece().makeAMove(destination);
        board[destination.y][destination.x].updateTile(board[origin.y][origin.x].getPiece(), true);
        board[origin.y][origin.x].updateTile(null, false);
    }



    public List<Point> getAllPlayerPieces(Point origin){
        List<Point> playerPieces = new ArrayList<Point>();
        Color turn = board[origin.y][origin.x].getPiece().getColor();
        Point playerPiece = new Point();


        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if(board[y][x].isHasPiece()) {
                    if (board[y][x].getPiece().getColor() == turn) {
                        playerPiece.x = x;
                        playerPiece.y = y;
                        playerPieces.add(new Point(playerPiece));
                    }
                }
            }
        }

        return playerPieces;
    }

    public List<Point> getAllEnemyPieces(Point origin){
        List<Point> enemyPieces = new ArrayList<Point>();
        Color turn = board[origin.y][origin.x].getPiece().getColor();
        Color enemyColor = null;
        Point enemyPiece = new Point();

        if(turn == Color.white){
            enemyColor = Color.black;
        } else {
            enemyColor = Color.white;
        }

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if(board[y][x].isHasPiece()) {
                    if (board[y][x].getPiece().getColor() == enemyColor) {
                        enemyPiece.x = x;
                        enemyPiece.y = y;
                        enemyPieces.add(new Point(enemyPiece));
                    }
                }
            }
        }

        return enemyPieces;
    }

    public List<Point> getAllEnemyMoves(Point origin){
        List<Point> enemyAllMoves = new ArrayList<Point>();
        List<Point> enemyMoves = new ArrayList<Point>();
        List<Point> enemyPieces = this.getAllEnemyPieces(origin);
        for (Point enemyPiece: enemyPieces) {
            enemyMoves = new ArrayList<Point>();
            if(this.getPiece(enemyPiece).getPieceType() != Type.KNIGHT) { // FIXME: After the Knight is working - delete this if
                enemyMoves = this.getPiece(enemyPiece).getAllMoves(this);
                enemyAllMoves.addAll(enemyMoves);
            }
        }
        return enemyAllMoves;
    }


    // FIXME: the bug is that the function do fake moves at the board...
    public List<Point> removeMoves(List<Point> playerMoves, Point origin, BackendBoard backendBoard) {
        BackendBoard newBoard = new BackendBoard();



        int mon = 0;
        List<Point> moves = new ArrayList<Point>(playerMoves);
        boolean kingWillKilled = false;
        Point king = new Point();
        List<Point> enemyMoves = new ArrayList<Point>();

        for (Point move: moves) {
            newBoard = new BackendBoard();

            newBoard.makeAMove(origin, move);
            origin = new Point(move);

            enemyMoves = newBoard.getAllEnemyMoves(origin);

            king = new Point(newBoard.getPlayerKing(origin));
            for (Point enemyMove: enemyMoves) {
                if(enemyMove.x == king.x && enemyMove.y == king.y){
                    moves.remove(mon);
                }
            }
            mon++;
        }

        return moves;
    }


    public Point getPlayerKing(Point origin){
        List<Point> enemyPieces = getAllEnemyPieces(origin);
        Color turn = board[origin.y][origin.x].getPiece().getColor();
        Point piece = new Point();
        Point king = new Point();


        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                piece.x = x;
                piece.y = y;
                if(board[y][x].isHasPiece()){
                    if ((board[y][x].getPiece().getColor() == turn) && (board[y][x].getPiece().getPieceType() == Type.KING)){
                        king = new Point(piece);
                        break;
                    }
                }
            }
        }

        return king;
    }

    @Override
    public String toString() {
        String stringBoard = "";
        for (Tile[] tiles: board) {
            for (Tile tile: tiles) {
                stringBoard += tile.toString();
            }
            stringBoard += "\n--------------------------------------------\n";
        }
        return stringBoard;
    }
}
