package com.example.mailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tags_email, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sp = (Spinner) findViewById(R.id.tag_mail);
        sp.setAdapter(adapter);
        EditText name = (EditText) findViewById(R.id.Nom);
        EditText mail = (EditText) findViewById(R.id.mail_adress);
        EditText body = (EditText) findViewById(R.id.body_mail);
        CheckBox re_send = (CheckBox) findViewById(R.id.copia);
        Button send = (Button) findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{"milbicis@gmail.com"});
                if(re_send.isChecked()){
                    emailIntent.putExtra(android.content.Intent.EXTRA_CC,
                            new String[]{mail.getText().toString()});
                }
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, name.getText().toString()+" "+sp.getSelectedItem().toString());
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body.getText().toString());
                startActivity(Intent.createChooser
                        (emailIntent, "Enviar correo..."));
            }
        });
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");


    }
}