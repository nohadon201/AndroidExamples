package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Titular[] datosE = new Titular[]{new Titular("Titol 1","Subtitol 1"),
            new Titular("Titol 2","Subtitol 2"),
            new Titular("Titol 3","Subtitol 3"),
            new Titular("Titol 4","Subtitol 4"),
            new Titular("Titol 5","Subtitol 5")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this,datosE);

        ListView lstOpciones = (ListView) findViewById(R.id.lstOpcions);

        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), datosE[arg2].getTitulo(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Modificate.class);
                Bundle b = new Bundle();

                b.putSerializable("DATOS", datosE);
                b.putInt("POS", arg2);
                i.putExtras(b);

                startActivityForResult(i, 1);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                datosE = (Titular[]) bundle.getSerializable("DATOS");
                AdaptadorTitulares adaptador = new AdaptadorTitulares(this, datosE);
                ListView lstOpciones = (ListView) findViewById(R.id.lstOpcions);
                lstOpciones.setAdapter(adaptador);
                Toast.makeText(this, data.getStringExtra("RESULTADO").toString(), Toast.LENGTH_LONG).show();
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, data.getStringExtra("RESULTADO").toString(), Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "hmmm", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

}
class AdaptadorTitulares extends ArrayAdapter {

    Activity context;

    Titular[] datosJ;
    public AdaptadorTitulares(Activity context,Titular[] datos ) {
        super(context, R.layout.listitem_titular, datos);
        this.context = (Activity) context;
        this.datosJ = datos;
    }




    // GetView s'executa per cada element de l'array de dades i el que fa
    // es "inflar" el layout del XML que hem creat

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // return super.getView(position, convertView, parent);
        // Inflem el Lauoyt
        LayoutInflater inflater = context.getLayoutInflater();
        // Sobre el layout crear (inflat) dupliquem el layour creat amb els objectes, view personals.
        View item = inflater.inflate(R.layout.listitem_titular, null);
        // OJOOOO!!!!! hemos de hacer el findViewById del item que tenemos inflado.
        TextView lblTitulo = (TextView) item.findViewById(R.id.lblTitulo);

        lblTitulo.setText(datosJ[position].getTitulo().toString());
        TextView lblSubTitulo = (TextView) item.findViewById(R.id.lblSubtitulo);
        // Log.i("Niko","3->"+datosE[position].getSubtitulo().toString() );
        lblSubTitulo.setText(datosJ[position].getSubtitulo().toString());
        // Log.i("Niko","4");
        return (item);
    }

}

