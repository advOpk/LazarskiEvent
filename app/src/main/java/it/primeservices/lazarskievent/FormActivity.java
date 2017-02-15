package it.primeservices.lazarskievent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class FormActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "it.primeservices.lazarskievent.MESSAGE";
    //Constructor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        EditText nameText = (EditText) findViewById(R.id.name_input);
        String nameString = nameText.getText().toString();
        EditText emailText = (EditText) findViewById(R.id.email_input);
        String emailString = emailText.getText().toString();

        if (nameText != null) {
            nameText.setText("");
        }
        if (emailText != null) {
            emailText.setText("");
        }

        /*
        WebView checkboxDescriptionText = (WebView)this.findViewById(R.id.checkbox_description_text);
        checkboxDescriptionText.getSettings().setJavaScriptEnabled(false);
        checkboxDescriptionText.loadDataWithBaseURL("", "<h2>Będziemy Cię informować m.in. o:</h2><ul><li>wydarzeniach na Uczelni</li><li>bezpłatnych warsztatach</li><li>ciekawych konferencjach</li><li>kursach językowaych</li><li>zniżkach w czesnym</li><li>nowych kierunkach studiów</li><li>aktualnych konkursach</li><ul>", "text/html", "UTF-8", "");
        */

        TextView checkboxDescriptionText = (TextView) findViewById(R.id.checkbox_description_text);
        checkboxDescriptionText.setText(Html.fromHtml("<h2>Będziemy Cię informować m.in. o:</h2><p>" +
                "wydarzeniach na Uczelni<br>bezpłatnych warsztatach<br>ciekawych konferencjach<br>" +
                "kursach językowaych<br>zniżkach w czesnym<br>nowych kierunkach studiów<br>" +
                "aktualnych konkursach</p>"));


        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                EventFormDbHelper eventDb = new EventFormDbHelper(getApplicationContext());
                Log.w("submitForm", "Submit Button");
                Intent intent = new Intent(getApplicationContext(), DisplayMessageActivity.class);

                EditText nameText = (EditText) findViewById(R.id.name_input);
                String nameString = nameText.getText().toString();
                EditText emailText = (EditText) findViewById(R.id.email_input);
                String emailString = emailText.getText().toString();

                int interestWpiaInt;
                int interestEizInt;

                intent.putExtra(EXTRA_MESSAGE, nameString + "" + emailString);

                CheckBox interestEizCheckbox = (CheckBox) findViewById(R.id.interest_eiz_checkbox);
                if(interestEizCheckbox.isChecked()) {
                    interestEizInt = 1;
                    Log.w("interestEizInt: ", "1");
                } else {
                    interestEizInt = 0;
                    Log.w("interestEizInt: ", "0");
                }

                CheckBox interestWpiaCheckbox = (CheckBox) findViewById(R.id.interest_wpia_checkbox);
                if(interestWpiaCheckbox.isChecked()) {
                    interestWpiaInt = 1;
                    Log.w("interestWpiaInt: ", "1");
                } else {
                    interestWpiaInt = 0;
                    Log.w("interestWpiaInt: ", "0");
                }

                if(isAgreament(view) == 1) {
                    eventDb.addContact(new Contact(nameString, emailString, interestEizInt, interestWpiaInt));
                    startActivity(intent);
                    eventDb.close();
                    listAll(view);
                } else {
                    Log.w("error", "error");
                }

                final CheckBox agreament1 = (CheckBox) findViewById(R.id.agreement_1);
                final CheckBox agreament2 = (CheckBox) findViewById(R.id.agreement_2);
                final CheckBox agreament3 = (CheckBox) findViewById(R.id.agreement_3);
                final CheckBox agreament4 = (CheckBox) findViewById(R.id.agreement_4);

                agreament1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) agreament1.setError(null);
                        else agreament1.setError("Pole jest obowiązkowe");
                    }
                });
                agreament2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) agreament2.setError(null);
                        else agreament2.setError("Pole jest obowiązkowe");
                    }
                });
                agreament3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) agreament3.setError(null);
                        else agreament3.setError("Pole jest obowiązkowe");
                    }
                });
                agreament4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) agreament4.setError(null);
                        else agreament4.setError("Pole jest obowiązkowe");
                    }
                });

            }
        });
    }

    private int isAgreament(View view){

        int agreament1val;
        int agreament2val;
        int agreament3val;
        int agreament4val;
        CheckBox agreament1 = (CheckBox) findViewById(R.id.agreement_1);
        CheckBox agreament2 = (CheckBox) findViewById(R.id.agreement_2);
        CheckBox agreament3 = (CheckBox) findViewById(R.id.agreement_3);
        CheckBox agreament4 = (CheckBox) findViewById(R.id.agreement_4);

        if(agreament1.isChecked()) {
            agreament1.setError(null);
            agreament1val = 1;
        } else {
            agreament1.setError("Pole jest obowiązkowe");
            agreament1val = 0;
        }

        if(agreament2.isChecked()) {
            agreament3.setError(null);
            agreament2val = 1;
        } else {
            agreament2.setError("Pole jest obowiązkowe");
            agreament2val = 0;
        }

        if(agreament3.isChecked()) {
            agreament3.setError(null);
            agreament3val = 1;
        } else {
            agreament3.setError("Pole jest obowiązkowe");
            agreament3val = 0;
        }

        if(agreament4.isChecked()) {
            agreament4.setError(null);
            agreament4val = 1;
        } else {
            agreament4.setError("Pole jest obowiązkowe");
            agreament4val = 0;
        }

        if ((agreament1val + agreament2val + agreament3val + agreament4val) == 4) {
            return 1;
        } else {
            return 0;
        }

    }


/*
    public void submitForm(View view) {
        EventFormDbHelper eventDb = new EventFormDbHelper(this);
        Log.w("submitForm", "Submit Button");
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText nameText = (EditText) findViewById(R.id.name_input);
        String nameString = nameText.getText().toString();
        EditText emailText = (EditText) findViewById(R.id.email_input);
        String emailString = emailText.getText().toString();

        int interestWpiaInt;
        int interestEizInt;

        intent.putExtra(EXTRA_MESSAGE, nameString + "" + emailString);

        CheckBox interestEizCheckbox = (CheckBox) findViewById(R.id.interest_eiz_checkbox);
        if(interestEizCheckbox.isChecked()) {
            interestEizInt = 1;
            Log.w("interestEizInt: ", "1");
        } else {
            interestEizInt = 0;
            Log.w("interestEizInt: ", "0");
        };

        CheckBox interestWpiaCheckbox = (CheckBox) findViewById(R.id.interest_wpia_checkbox);
        if(interestWpiaCheckbox.isChecked()) {
            interestWpiaInt = 1;
            Log.w("interestWpiaInt: ", "1");
        } else {
            interestWpiaInt = 0;
            Log.w("interestWpiaInt: ", "0");
        };

        eventDb.addContact(new Contact(nameString, emailString, interestEizInt, interestWpiaInt));
        startActivity(intent);

        listAll(this);

        //EventFormDbHelper eventDb = new EventFormDbHelper(this);
        //eventDb.addContact(new Contact("Ravi", "opk1@wp.pl"));
    }
*/

    public void listAll(View view) {
        EventFormDbHelper eventDb = new EventFormDbHelper(this);
        //Intent intent = new Intent(this, DisplayMessageActivity);
        // Reading all contacts
        Log.w("FormActivity.Reading: ", "Reading all contacts..");
        List<Contact> contacts = eventDb.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Name: " + cn.getName() + " ,Email: " + cn.getEmail() + " ,EiZ: " + cn.getInterestEiz() + " ,WPiA: " + cn.getInterestWpia();
            // Writing Contacts to log
            Log.w("Name: ", log);
        }
    }

}
