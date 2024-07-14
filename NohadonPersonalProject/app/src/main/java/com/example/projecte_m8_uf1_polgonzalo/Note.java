package com.example.projecte_m8_uf1_polgonzalo;

import java.util.ArrayList;
import java.util.Date;

public class Note {
    public static ArrayList<Note> noteArrayList = new ArrayList<Note>();
    public static final String NOTE_EDIT = "noteEdit";
    private int id;
    private String title;
    private String Description;
    private Date deleted;

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        Description = description;
        this.deleted=null;
    }

    public Note(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        Description = description;
        this.deleted = deleted;
    }

    public static Note getNoteForId(int passedId) {
        for (Note note: noteArrayList) {
            if(note.id==passedId){
                return note;
            }
        }
        return null;
    }

    public static ArrayList<Note> getNonDeletedNotes(){
        ArrayList<Note> notes = new ArrayList<>();
        for (com.example.projecte_m8_uf1_polgonzalo.Note note:
             noteArrayList) {
            if(note.deleted==null){
                notes.add(note);
            }
        }
        return notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
