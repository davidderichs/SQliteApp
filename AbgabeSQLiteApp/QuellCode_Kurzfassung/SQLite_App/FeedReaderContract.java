package davidderichs.SQLite_App;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FeedReaderContract {

    FeedReaderDbHelper mDbHelper;

    public FeedReaderContract(Context context){
        this.mDbHelper = new FeedReaderDbHelper(context);
    }

    public void InsertEntry(String name, String nachname, String spitzname, String telefonnummer)
    {
        //Log.d("", host + guest +result);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        //String id = String.format("%d", getNumberOfEntries(FeedEntry.TABLE_NAME) + 1);
        int id = getNumberOfEntries(FeedEntry.TABLE_NAME) + 1;
        Log.d("id", "getNumberOfEntries " +id);
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_ENTRY_ID, id);
        values.put(FeedEntry.COLUMN_NAME_NAME, name);
        values.put(FeedEntry.COLUMN_NAME_LASTNAME, nachname);
        values.put(FeedEntry.COLUMN_NAME_NICKNAME, spitzname);
        values.put(FeedEntry.COLUMN_NAME_PHONENUMBER, telefonnummer);
        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedEntry.TABLE_NAME,
                null,
                values);
    }

    public void deleteAllEntries(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(db, 0, 1 );
    }

    private int getNumberOfEntries(String tableName) {
        String countQuery = "SELECT * FROM " + tableName;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public Cursor ReadEntry()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedEntry.COLUMN_NAME_ENTRY_ID,
                FeedEntry.COLUMN_NAME_NAME,
                FeedEntry.COLUMN_NAME_LASTNAME,
                FeedEntry.COLUMN_NAME_NICKNAME,
                FeedEntry.COLUMN_NAME_PHONENUMBER,
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedEntry.COLUMN_NAME_ENTRY_ID + " ASC";
        Cursor c = db.query(
                FeedEntry.TABLE_NAME, // The table to query
                projection, // The columns to return
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                sortOrder // The sort order
        );
        return c;
    }
}
