package com.example.user.appfinal.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by user on 2017/6/17.
 */

public class FoodContract {

    public static final String AUTHORITY = "com.example.user.appfinal";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_TASKS = "foodstore";

    public static final class FoodEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

        public static final String TABLE_NAME = "foodstore";
        public static final String COLUMN_FOOD_NAME = "food_name";
        public static final String COLUMN_BUY_TIME = "buy_time";
        public static final String COLUMN_ALERT_TIME = "alert_time";
        public static final String COLUMN_REMARK = "remark";


    }
}
