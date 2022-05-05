package com.github.jbullers.tennis;

public class TennisGame1 implements TennisGame {

    private final String serverName;
    private final String receiverName;
    private Score score = new LoveAll();

    public TennisGame1(String serverName, String receiverName) {
        this.serverName = serverName;
        this.receiverName = receiverName;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(serverName)) {
            score = score.serverPoint();
        } else {
            score = score.receiverPoint();
        }
    }

    public String getScore() {
        return score.callForScore(serverName, receiverName);
    }

    private sealed interface Score {
        Score serverPoint();

        Score receiverPoint();

        String callForScore(String serverName, String receiverName);
    }

    private record LoveAll() implements Score {

        @Override
        public Score serverPoint() {
            return new NumericalScore(1, 0);
        }

        @Override
        public Score receiverPoint() {
            return new NumericalScore(0, 1);
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return "Love-All";
        }
    }

    private record NumericalScore(int serverScore, int receiverScore) implements Score {

        @Override
        public Score serverPoint() {
            int updatedServerScore = serverScore + 1;
            int delta = updatedServerScore - receiverScore;
            if (delta == 0 && updatedServerScore >= 3) {
                return new Deuce();
            } else if (delta == 1 && updatedServerScore >= 4) {
                return new AdvantageServer();
            } else if (delta >= 2 && updatedServerScore >= 4) {
                return new WinServer();
            } else {
                return new NumericalScore(updatedServerScore, receiverScore);
            }
        }

        @Override
        public Score receiverPoint() {
            int updatedReceiverScore = receiverScore + 1;
            int delta = updatedReceiverScore - serverScore;
            if (delta == 0 && updatedReceiverScore >= 3) {
                return new Deuce();
            } else if (delta == 1 && updatedReceiverScore >= 4) {
                return new AdvantageReceiver();
            } else if (delta >= 2 && updatedReceiverScore >= 4) {
                return new WinReceiver();
            } else {
                return new NumericalScore(serverScore, updatedReceiverScore);
            }
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return serverScore != receiverScore ?
                    callForScore(serverScore) + "-" + callForScore(receiverScore) :
                    callForScore(serverScore) + "-All";
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

    private record Deuce() implements Score {

        @Override
        public Score serverPoint() {
            return new AdvantageServer();
        }

        @Override
        public Score receiverPoint() {
            return new AdvantageReceiver();
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return "Deuce";
        }
    }

    private record AdvantageServer() implements Score {

        @Override
        public Score serverPoint() {
            return new WinServer();
        }

        @Override
        public Score receiverPoint() {
            return new Deuce();
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return "Advantage " + serverName;
        }
    }

    private record AdvantageReceiver() implements Score {

        @Override
        public Score serverPoint() {
            return new Deuce();
        }

        @Override
        public Score receiverPoint() {
            return new WinReceiver();
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return "Advantage " + receiverName;
        }
    }

    private record WinServer() implements Score {

        @Override
        public Score serverPoint() {
            return new WinServer();
        }

        @Override
        public Score receiverPoint() {
            return new WinServer();
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return "Win for " + serverName;
        }
    }

    private record WinReceiver() implements Score {

        @Override
        public Score serverPoint() {
            return new WinReceiver();
        }

        @Override
        public Score receiverPoint() {
            return new WinReceiver();
        }

        @Override
        public String callForScore(String serverName, String receiverName) {
            return "Win for " + receiverName;
        }
    }
}
