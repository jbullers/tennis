package com.github.jbullers.tennis;

public class TennisGame1 implements TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private final String serverName;
    private final String receiverName;

    public TennisGame1(String serverName, String receiverName) {
        this.serverName = serverName;
        this.receiverName = receiverName;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(serverName))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (m_score1 == m_score2) {
            switch (m_score1) {
                case 0:
                    score = "Love-All";
                    break;
                case 1:
                    score = "Fifteen-All";
                    break;
                case 2:
                    score = "Thirty-All";
                    break;
                default:
                    score = "Deuce";
                    break;

            }
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            int minusResult = m_score1 - m_score2;
            if (minusResult == 1) score = "Advantage " + serverName;
            else if (minusResult == -1) score = "Advantage " + receiverName;
            else if (minusResult >= 2) score = "Win for " + serverName;
            else score = "Win for " + receiverName;
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) tempScore = m_score1;
                else {
                    score += "-";
                    tempScore = m_score2;
                }
                switch (tempScore) {
                    case 0:
                        score += "Love";
                        break;
                    case 1:
                        score += "Fifteen";
                        break;
                    case 2:
                        score += "Thirty";
                        break;
                    case 3:
                        score += "Forty";
                        break;
                }
            }
        }
        return score;
    }
}
