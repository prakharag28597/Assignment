package neuromancers.assignament1;

/**
 * Created by prakharag on 31-03-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;


public class LeafyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "freshleafy.db";
    public static final String TABLE_NAME = "items_sold";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM_NAME";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "SUBCATEGORY";
    public static final String COL_5 = "PRICE";
    public static final String COL_6 = "QTY";


    public LeafyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ITEM_NAME TEXT,CATEGORY TEXT,SUBCATEGORY TEXT,PRICE TEXT,QTY TEXT)");
        Log.d("kr","yes");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //removes the table if it already exists
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String itemName,String  category,String subcategory,String price,String qty) {
        //to insert data to the column
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,itemName);
        contentValues.put(COL_3,category);
        contentValues.put(COL_4,subcategory);
        contentValues.put(COL_5,price);
        contentValues.put(COL_6,qty);
        long x= db.insert(TABLE_NAME,null ,contentValues);

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String qty,String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_5,price);
        contentValues.put(COL_6,qty);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        Log.e("lets","yo");
        return true;
    }


    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("delete * from "+ TABLE_NAME);
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}