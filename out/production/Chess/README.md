private List<Point> removeMoves(List<Point> moves, Point origin){
        BackendBoard newBoard = new BackendBoard();
        newBoard = this.backendBoard;
        int mon = 0;
        boolean kingWillKilled = false;
        List<Point> enemyMoves = new ArrayList<Point>();
        Point king = new Point();
        //backendBoard.getPiece(origin).getAllMoves()

        for (Point move: moves) {
            newBoard = this.backendBoard;
            newBoard.makeAMove(move, origin);
            enemyMoves = getAllEnemyMoves(newBoard, origin);
            king = getKing(newBoard, origin);
            for (Point enemyMove: enemyMoves) {
                if(enemyMove == king){
                    moves.remove(mon);
                }
            }
            mon++;
        }

        return moves;
    }

    public List<Point> getAllEnemyMoves(BackendBoard board, Point origin){
        List<Point> enemyPieces = getAllEnemyPieces(board, origin);
        List<Point> enemyMovesForPiece = new ArrayList<Point>();
        List<Point> enemyMoves = new ArrayList<Point>();

        for (Point enemyPiece: enemyPieces) {
            enemyMovesForPiece = (board.getPiece(enemyPiece).getAllMoves(board));
            enemyMoves.addAll(enemyMovesForPiece);
        }

        return enemyMoves;
    }

    public List<Point> getAllEnemyPieces(BackendBoard board, Point origin){
        List<Point> enemyPieces = new ArrayList<Point>();
        Point piece = new Point();
        Color color = null;

        if (board.getPiece(origin).getColor() == Color.white)
            color = Color.black;
        if (board.getPiece(origin).getColor() == Color.black)
            color = Color.white;

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                piece.x = x;
                piece.y = y;
                if(board.getHasPiece(piece) != false){
                    if (board.getPiece(piece).getColor() == color){
                        enemyPieces.add(piece);
                    }
                }
            }
        }

        return enemyPieces;
    }

    public Point getKing(BackendBoard board, Point origin){
        List<Point> enemyPieces = new ArrayList<Point>();
        Point piece = new Point();
        Point king = new Point();


        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                piece.x = x;
                piece.y = y;
                if(board.getHasPiece(piece) != false){
                    if (board.getPiece(piece).getColor() == board.getPiece(piece).getColor()){
                        king = piece;
                        break;
                    }
                }
            }
        }

        return king;
    }
