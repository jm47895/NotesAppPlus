package com.jordanmadrigal.notesappplus.ui.notelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jordanmadrigal.notesappplus.R;
import com.jordanmadrigal.notesappplus.repository.NoteRepository;
import com.jordanmadrigal.notesappplus.ui.note.NoteActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NotesListActivity extends DaggerAppCompatActivity {

    private static final String TAG = "NotesListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);


    }
}
