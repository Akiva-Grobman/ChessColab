package GameObjects;

abstract class piece {

    protected int row;
    protected int column;
    protected boolean isAlive;
    protected String color;
    protected Type pieceType;


    enum Type {
        KING,
        QUEEN,
        ROOK,   
        BISHOP, 
        KNIGHT, 
        PAWN    
    }

    public piece(Type pieceType, String color) {
        this.isAlive = true;
        this.pieceType = pieceType;
        this.color = color;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }


    abstract void move();



}
