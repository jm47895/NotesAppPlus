package com.jordanmadrigal.notesappplus;

import android.app.Application;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.jordanmadrigal.notesappplus.models.Note;
import com.jordanmadrigal.notesappplus.persistence.NoteDao;
import com.jordanmadrigal.notesappplus.persistence.NoteDatabase;
import com.jordanmadrigal.notesappplus.util.TestUtil;

import org.junit.After;
import org.junit.Before;

public abstract class NoteDatabaseTest {

    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init(){
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(), NoteDatabase.class).build();
    }

    @After
    public void finish(){
        noteDatabase.close();
    }
}
