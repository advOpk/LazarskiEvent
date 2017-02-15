package it.primeservices.lazarskievent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(FormActivity.EXTRA_MESSAGE);
        //TextView textView = new TextView(this);
        //message = "";
        //textView.setTextSize(40);
        //textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        //layout.addView(textView);
    }

    public void backToForm(View view) {
        setContentView(R.layout.activity_form);
    }
}
