package com.example.treballsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MiAgendaBBDD extends SQLiteOpenHelper {
	
	// En este caso la BBDD solo tiene una tabla, asi que solo creamos un sql de creacion�+
	// Si hubiese mas tablas, tendrianmos que crear todas las tablas y relaciones aqu�,
	
	String sqlCreacion ="CREATE TABLE agenda (id integer primary key autoincrement, " +
			"nom text  not null, email text not null);";
	public MiAgendaBBDD(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sqlCreacion);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Nom�s s'executa quan la versi� de la BBDD amb la que cridem es superior a l'actual
		// S'hauria de fer una micraci� de dades. Nosaltres simplment esborrament les dades
		// antigues i crearem la nova estructura.
		
		db.execSQL("DROP TABLE IF EXISTS Agenda");
		
		db.execSQL(sqlCreacion);
		// Tamb� �s podria fer: 
		// onCreate(db);
	}

}
