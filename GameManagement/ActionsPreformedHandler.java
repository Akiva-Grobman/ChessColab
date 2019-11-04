package GameManagement;

import GameLogic.BackendBoard;

public class ActionsPreformedHandler {

    private BackendBoard board;

    ActionsPreformedHandler(BackendBoard backendBoard) {
        this.board = backendBoard;
    }



    public BackendBoard getBackendBoardInstance() {
        return board;
    }
}
