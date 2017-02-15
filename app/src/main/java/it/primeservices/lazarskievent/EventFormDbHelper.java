package it.primeservices.lazarskievent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


class EventFormDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "LazarskiEvent.db";

    //Main table for form
    private static final String TABLE_FORM = "form_data";

    //Contacts columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_EMAIL = "email";

    //Checkbox columns
    private static final String KEY_INTEREST_EIZ = "interest_eiz";
    private static final String KEY_INTEREST_WPIA = "interest_wpia";
    private static final String KEY_INTEREST_M = "interest_m";
    private static final String KEY_INTEREST_SP = "interest_sp";
    private static final String KEY_INTEREST_PBP = "interest_pbpz";


    private static final String DATABASE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_FORM + " (" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_DATE + " TEXT," +
            KEY_NAME + " TEXT," +
            KEY_EMAIL + " TEXT," +
            KEY_INTEREST_EIZ + " INTEGER," +
            KEY_INTEREST_WPIA + " INTEGER," +
            KEY_INTEREST_M + " INTEGER," +
            KEY_INTEREST_SP + " INTEGER," +
            KEY_INTEREST_PBP + " INTEGER)";


    EventFormDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORM);
        onCreate(db);

    }
    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    //add Contact
    void addContact(Contact contact) {
        //EventFormDbHelper eventDbHelper = new EventFormDbHelper(getContext());
        //SQLiteDatabase eventDb = eventDbHelper.getWritableDatabase();
        SQLiteDatabase eventDb = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());

        values.put(KEY_INTEREST_EIZ, contact.getInterestEiz());
        values.put(KEY_INTEREST_WPIA, contact.getInterestWpia());

        eventDb.insert(TABLE_FORM, null, values);
        eventDb.close();

    }

    //get Contact
    Contact getContact(int id) {
        SQLiteDatabase eventDb = this.getReadableDatabase();

        Cursor cursor = eventDb.query(TABLE_FORM, new String[] {
                KEY_ID, KEY_DATE, KEY_NAME, KEY_EMAIL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        cursor.close();
        return contact;

    }

    /* get all Contacts */
    List<Contact> getAllContacts() {
        Log.w("EventFormDbHelper.List", "Starting...");
        List<Contact> contactList = new ArrayList<>();

        /* Select All Query */
        String selectQuery = "SELECT * FROM " + TABLE_FORM;

        SQLiteDatabase eventDb = this.getWritableDatabase();
        Cursor cursor = eventDb.rawQuery(selectQuery, null);

        /* looping through all rows and adding to list */
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();

                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setDate(cursor.getString(1));
                contact.setName(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setInterestEiz(cursor.getInt(4));
                contact.setInterestWpia(cursor.getInt(5));

                contactList.add(contact);
            } while ( cursor.moveToNext() );
        }
        cursor.close();

        return contactList;
    }
}
