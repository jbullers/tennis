package com.github.jbullers.tennis;

public class TennisGame1 implements TennisGame {

    private int serverScore;
    private int receiverScore;
    private final String serverName;
    private final String receiverName;

    public TennisGame1(String serverName, String receiverName) {
        this.serverName = serverName;
        this.receiverName = receiverName;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(serverName))
            serverScore += 1;
        else
            receiverScore += 1;
    }

    public String getScore() {
        if (serverScore == receiverScore) {
            return switch (serverScore) {
                case 0 -> "Love-All";
                case 1 -> "Fifteen-All";
                case 2 -> "Thirty-All";
                default -> "Deuce";
            };
        } else if (serverScore >= 4 || receiverScore >= 4) {
            int minusResult = serverScore - receiverScore;
            if (minusResult == 1) return "Advantage " + serverName;
            else if (minusResult == -1) return "Advantage " + receiverName;
            else if (minusResult >= 2) return "Win for " + serverName;
            else return "Win for " + receiverName;
        } else {
            return callForScore(serverScore) + "-" + callForScore(receiverScore);
        }
    }

    private static String callForScore(int score) {
        return switch (score) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> throw new IllegalArgumentException("Expected score of 0-3 inclusive");
        };
    }
}
