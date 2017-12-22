package asuper.zhangpan.zdbmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhangpan on 17/12/22.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "zdatabase.db";
    public static final String TB_USER = "user";

    /**
     * user default setting
     * @param context
     */
    public SQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * customer setting
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 0:

                break;

            default:

                break;
        }
    }
}
