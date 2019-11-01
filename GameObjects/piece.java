package GameObjects;

abstract class piece {

    private int row;
    private int col;
    private boolean isAlive;
    private String color;


    enum Type {
        KING,
        QUEEN,
        ROOK,   
        BISHOP, 
        KNIGHT, 
        PAWN    
    }

    public piece() {
        this.isAlive = true;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


    abstract void move();



}
