package com.example.projecte_m8_uf1_polgonzalo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.aware.IdentityChangedListener;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME="NotesBBDD";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="Note";
    private static final String COUNTER="Counter";
    private static final String ID_FIELD="Id";
    private static final String TITLE_FIELD="Title";
    private static final String DESCRIPTION_FIELD="Description";
    private static final String DELETED_FIELD="Deleted";

    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }
    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager==null){
            sqLiteManager=new SQLiteManager(context);
        }
        return sqLiteManager;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DESCRIPTION_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");
        sqLiteDatabase.execSQL(sql.toString());

    }
    public void addNoteToDatabase(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(TITLE_FIELD, note.getTitle());
        contentValues.put(DESCRIPTION_FIELD, note.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public void populateNoteListArray(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null)){
            if(result.getCount()!=0){
                while(result.moveToNext()){
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    String description = result.getString(3);
                    String deleted = result.getString(4);
                    Date deletedd = getDateFromString(deleted);
                    Note note = new Note(id,title,description,deletedd);
                    Note.noteArrayList.add(note);
                }
            }
        }
    }

    public void UpdateInDB(Note n){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, n.getId());
        contentValues.put(TITLE_FIELD, n.getTitle());
        contentValues.put(DESCRIPTION_FIELD, n.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(n.getDeleted()));
        sqLiteDatabase.update(TABLE_NAME, contentValues,ID_FIELD +" =? ", new String[]{ String.valueOf( n.getId() ) });

    }
    private String getStringFromDate(Date date){
        if(date==null){
            return null;
        }
        return dateFormat.format(date);
    }
    private Date getDateFromString(String string){
        try{
            return dateFormat.parse(string);
        }catch (ParseException | NullPointerException e){
            return null;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
