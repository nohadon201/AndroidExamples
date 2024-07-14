package com.example.projecte_m8_uf1_polgonzalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView notesListView;
    private Button configuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();
    }
    private void loadFromDBToMemory(){
        SQLiteManager manager = SQLiteManager.instanceOfDatabase(this);
        manager.populateNoteListArray();
    }
    private void setOnClickListener() {
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Note noteSelected = (Note) notesListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(),EditData.class);
                editNoteIntent.putExtra(noteSelected.NOTE_EDIT,noteSelected.getId());
                startActivity(editNoteIntent);
            }
        });
        configuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(i);
            }
        });
    }
    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.getNonDeletedNotes());
        notesListView.setAdapter(noteAdapter);
    }

    private void initWidgets() {
        notesListView= findViewById(R.id.noteListView);
        configuration= findViewById(R.id.config);
    }

    public void newNote(View view) {
        Intent intent = new Intent(this, EditData.class);
        startActivity(intent);
    }
}