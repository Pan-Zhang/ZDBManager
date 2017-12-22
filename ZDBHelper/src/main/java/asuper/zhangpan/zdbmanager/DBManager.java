package asuper.zhangpan.zdbmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangpan on 17/12/22.
 */

public class DBManager<T> {

    Context context;

    public DBManager(Context context){
        this.context = context;
    }

    public DBManager(Context context, String path){
        this.context = new DatabaseContext(context, path);
    }

    public List<T> query(Class<T> cls, String sql, String[] args) {
        SQLiteDatabase db = new SQLiteHelper(context).getWritableDatabase();
        List<T> list = new ArrayList<T>();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(sql, args);
            T object = cls.newInstance();
            Field[] fildes = cls.getDeclaredFields();
            while (cursor.moveToNext()){
                for(int i=0; i<cursor.getColumnCount(); i++){
                    for(int j=0; j<fildes.length; j++){
                        if(cursor.getColumnName(i).equals(fildes[j].getName())){
                            Field idField = cls.getDeclaredField(fildes[j].getName());
                            Class<?> fieldType = idField.getType();
                            if(fieldType.equals(Integer.class)){
                                idField.set(object, cursor.getInt(i));
                            }
                            else if(fieldType.equals(String.class)){
                                idField.set(object, cursor.getString(i));
                            }
                            else if(fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)){
                                idField.set(object, cursor.getInt(i)!=0);
                            }
                            else if(fieldType.equals(float.class)){
                                idField.set(object, cursor.getFloat(i));
                            }
                        }
                    }
                }
                list.add(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
            return list;
        }
    }

}
