package app.simone;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Giacomo on 26/06/2017.
 */

public class Match extends RealmObject {
    private Date gameDate;
    private int score;



    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
