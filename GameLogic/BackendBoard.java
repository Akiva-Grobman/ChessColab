package GameLogic;

import GUI.GUIBoard;
import GameObjects.*;
import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BackendBoard implements Cloneable {

    private Tile [][] board;
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

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

    public BackendBoard(BackendBoard board) throws CloneNotSupportedException {
        this.board = new Tile[BackendBoard.COLUMNS][BackendBoard.ROWS];
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++) {
                this.board[y][x] = (Tile) board.board[y][x].clone();
            }
        }
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

    public void makeAMove(Point origin, Point destination){
        Tile originTile = board[origin.y][origin.x];
        Tile destinationTile = board[destination.y][destination.x];
        Piece pieceWithNewPointer;
        try {
            pieceWithNewPointer = (Piece) originTile.getPiece().clone();
        } catch (CloneNotSupportedException e) {
            // will not have a new pointer
            pieceWithNewPointer = originTile.getPiece();
        }
        pieceWithNewPointer.makeAMove(destination);
        destinationTile.updateTile(pieceWithNewPointer, true);
        originTile.updateTile(null, false);
    }

    public void clearTile(Point position){
        board[position.y][position.x].updateTile(null, false);
    }

    public void changePieceType(Point position, Piece newPieceType){
        board[position.y][position.x].addPiece(newPieceType);
    }

    private Color getPieceColor(int row){
        if(row == 0 || row == 1){
            return Color.black;
        } else {
            return Color.white;
        }
    }

    public Piece getPiece(Point position) {
        return board[position.y][position.x].getPiece();
    }

    public boolean getHasPiece(Point position) {
        return board[position.y][position.x].isHasPiece();
    }

    public List<Point> getAllEnemyMoves(Point origin){
        List<Point> allEnemyMoves = new ArrayList<>();
        List<Point> currentPieceMoves;
        List<Point> enemyPieces = getAllEnemyPieces(origin);
        for (Point enemyPiecePosition: enemyPieces) {
            currentPieceMoves = getPiece(enemyPiecePosition).getAllMoves(this);
            allEnemyMoves.addAll(currentPieceMoves); //todo might want to check for duplicates
        }
        return allEnemyMoves;
    }

    public Point getPlayerKingPosition(Point origin) {
        List<Point> playersPieces = getAllPlayerPieces(origin);
        Color playersColor = board[origin.y][origin.x].getPiece().getColor();

        for (Point currentPiecePosition: playersPieces) {
            if(board[currentPiecePosition.y][currentPiecePosition.x].getPiece() instanceof King){
                if(board[currentPiecePosition.y][currentPiecePosition.x].getPiece().getColor() == playersColor){
                    return currentPiecePosition;
                }
            }
        }
        throw new Error(" Game over this code should not have been called!\n there is only one king \n current players color is" + playersColor);
    }

    private List<Point> getAllPlayerPieces(Point origin){
        List<Point> playersPieces = new ArrayList<>();
        Color playersColor = board[origin.y][origin.x].getPiece().getColor();
        Point playerPiecePosition;

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                if(board[y][x].isHasPiece()) {
                    if (board[y][x].getPiece().getColor() == playersColor) {
                        playerPiecePosition = new Point(x,y);
                        playersPieces.add(new Point(playerPiecePosition));
                    }
                }
            }
        }

        return playersPieces;
    }

    private List<Point> getAllEnemyPieces(Point origin){
        List<Point> enemyPieces = new ArrayList<>();
        Tile originTile = board[origin.y][origin.x];
        Color playersColor = originTile.getPiece().getColor();
        Color enemyColor;
        Point enemyPiece = new Point();

        if(playersColor == Color.white){
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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
