package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificate extends AppCompatActivity implements View.OnClickListener {

    public Titular[] datos;
    public String t;
    public String st;
    int pos2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_title);

        Bundle bundle = this.getIntent().getExtras();
        datos = (Titular[]) bundle.getSerializable("DATOS");

        int position = bundle.getInt("POS");
        pos2 = position;
        Button modificar = (Button) findViewById(R.id.Modify);
        Button cancelar = (Button) findViewById(R.id.cancel);
        modificar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        Bundle b = new Bundle();
        EditText etTitulo = (EditText) findViewById(R.id.Title);
        EditText etSubtitulo = (EditText) findViewById(R.id.Subtitle);
        datos[pos2] = new Titular(etTitulo.getText().toString(), etSubtitulo.getText().toString());
        switch (v.getId()) {
            case R.id.Modify:
                b.putSerializable("DATOS", datos);
                i.putExtras(b);
                i.putExtra("RESULTADO", "ok");
                setResult(RESULT_OK, i);
                finish();
                break;
            case R.id.cancel:
                i.putExtra("RESULTADO", "Operacion cancelada");
                setResult(RESULT_CANCELED, i);
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}