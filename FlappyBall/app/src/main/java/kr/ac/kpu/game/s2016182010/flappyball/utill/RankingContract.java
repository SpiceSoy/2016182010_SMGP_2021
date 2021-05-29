package kr.ac.kpu.game.s2016182010.flappyball.utill;

import android.provider.BaseColumns;

public class RankingContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private RankingContract() {}

    /* Inner class that defines the table contents */
    public static class RankEntry implements BaseColumns {
        public static final String TABLE_NAME = "ranking";
        public static final String COLUMN_NAME_PLAYER_NAME = "name";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}