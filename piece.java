abstract class piece {

    private int row;
    private int col;
    private boolean isAlive;
    private String color;


    enum Type {
        KING,
        QUEEN,
        ROOK,   // צריח
        BISHOP, // רץ
        KNIGHT, // פרש
        PAWN    // חייל
    }

    public piece() {

    }

    public int getX() {
        return this.row;
    }

    public int getY() {
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
