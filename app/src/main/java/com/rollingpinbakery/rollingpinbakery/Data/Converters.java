package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Created by rudst on 2/12/2018.
 */

public class Converters {
    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
