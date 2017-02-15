package it.primeservices.lazarskievent;

import android.util.Log;

class Contact {
    private int _id;
    private String _name;
    private String _date;
    private String _email;

    private int _interest_eiz;
    private int _interest_wpia;
    private int _interest_m;
    private int _interest_sp;
    private int _interest_pbpz;


    Contact() {}
    Contact(int id, String date, String name, String email) {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        Log.w("Contact: ", "4");
        this._id = id;
        this._date = ts;
        this._name = name;
        this._email = email;
    }
    Contact(String name, String email, int interest_eiz, int interest_wpia) {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        Log.w("Contact 3: ", ts);
        this._date = ts;
        this._name = name;
        this._email = email;
        this._interest_eiz = interest_eiz;
        this._interest_wpia = interest_wpia;
    }


    int getID() {
        return this._id;
    }
    void setID(int id) {
        this._id = id;
    }

    String getDate() {
        return this._date;
    }
    void setDate(String date) {
        this._date = date;
    }

    public String getName() {
        return this._name;
    }
    public void setName(String name) {
        this._name = name;
    }

    String getEmail() {
        return this._email;
    }
    void setEmail(String email) {
        this._email = email;
    }

    int getInterestEiz() {
        return this._interest_eiz;
    }
    void setInterestEiz(int interest_eiz) {
        this._interest_eiz = interest_eiz;
    }

    int getInterestWpia() {
        return this._interest_wpia;
    }
    void setInterestWpia(int interest_wpia) {
        this._interest_wpia = interest_wpia;
    }
}
