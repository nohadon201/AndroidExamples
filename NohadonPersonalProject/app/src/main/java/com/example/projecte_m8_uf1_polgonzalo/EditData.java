package com.example.projecte_m8_uf1_polgonzalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EditData extends AppCompatActivity {
    private EditText TitleEditText,DescriptionEditText;
    private Note noteSelected;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        initWidgets();
        checkForEditNote();
        updateTextSize();
    }
    public void updateTextSize(){
        TitleEditText.setTextSize(SettingsActivity.TextSize);
        DescriptionEditText.setTextSize(SettingsActivity.TextSize);
    }

    private void checkForEditNote() {
        Intent previusIntent = getIntent();
        int passedId = previusIntent.getIntExtra(Note.NOTE_EDIT,-1);
        noteSelected = Note.getNoteForId(passedId);
        if(noteSelected!=null){
            TitleEditText.setText(noteSelected.getTitle());
            DescriptionEditText.setText(noteSelected.getDescription());
        }else{
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    private void initWidgets() {
        TitleEditText= findViewById(R.id.Title);
        DescriptionEditText= findViewById(R.id.Description);
        deleteButton=findViewById(R.id.deleteButton);
    }

    public void saveNote(View view) {
        SQLiteManager manager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(TitleEditText.getText());
        String description = String.valueOf(DescriptionEditText.getText());
        if(noteSelected==null){
            int id = Note.noteArrayList.size();
            Note note = new Note(id,title,description);
            Note.noteArrayList.add(note);
            manager.addNoteToDatabase(note);
        }else{
            noteSelected.setTitle(title);
            noteSelected.setDescription(description);
            manager.UpdateInDB(noteSelected);
        }

        finish();
    }
    public void deleteNote(View view){
        this.noteSelected.setDeleted(new Date());
        SQLiteManager manager = SQLiteManager.instanceOfDatabase(this);
        manager.UpdateInDB(this.noteSelected);
        finish();
    }
}