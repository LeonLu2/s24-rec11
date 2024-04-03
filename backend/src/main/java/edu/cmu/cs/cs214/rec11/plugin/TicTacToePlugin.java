package edu.cmu.cs.cs214.rec11.plugin;

import edu.cmu.cs.cs214.rec11.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec11.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec11.games.TicTacToe;

/**
 * A TicTacToe game plugin.
 */
public class TicTacToePlugin implements GamePlugin<TicTacToe.Player> {

    private static final String GAME_NAME = "TicTacToe";
    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return TicTacToe.SIZE;
    }

    @Override
    public int getGridHeight() {
        return TicTacToe.SIZE;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        this.game = new TicTacToe();
        for (int i = 0; i < TicTacToe.SIZE; i++) {
            for (int j = 0; j < TicTacToe.SIZE; j++) {
                framework.setSquare(i, j, "");
            }
        }
        framework.setFooterText("Player X's turn");
    }

    @Override
    public void onNewMove() {
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.currentPlayer().opponent().toString());
        framework.setFooterText("Player " + game.currentPlayer().toString() + "'s turn");
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        TicTacToe.Player winner = game.winner();
        if (winner == null) {
            return "The game is a tie!";
        } else {
            return "Player " + winner + " wins!";
        }
    }

    @Override
    public void onGameClosed() {
    }

    @Override
    public TicTacToe.Player currentPlayer() {
        return game.currentPlayer();
    }
}