package com.jordanmadrigal.notesappplus;

import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.jordanmadrigal.notesappplus.models.Note;
import com.jordanmadrigal.notesappplus.util.LiveDataTestUtil;
import com.jordanmadrigal.notesappplus.util.TestUtil;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NoteDaoTest extends NoteDatabaseTest {

    private static final String TEST_TITLE = "This is a test title";
    private static final String TEST_CONTENT = "This is some test content";
    private static final String TEST_TIMESTAMP = "08-2018";

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    //Insert, Read, Delete
    @Test
    public void testInsertReadDelete() throws Exception{
        Note note = new Note(TestUtil.TEST_NOTE_1);

        //insert
        getNoteDao().insertNote(note).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);
        assertEquals(note.getContent(), insertedNotes.get(0).getContent());
        assertEquals(note.getTimestamp(), insertedNotes.get(0).getTimestamp());
        assertEquals(note.getTitle(), insertedNotes.get(0).getTitle());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note, insertedNotes.get(0));

        //delete
        getNoteDao().deleteNote(note).blockingGet();

        //confirm database is empty
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0, insertedNotes.size());
    }

    //Insert, Read, Update, Read, Delete
    @Test
    public void testInsertReadUpdateReadDelete() throws Exception{
        Note note = new Note(TestUtil.TEST_NOTE_1);

        //insert
        getNoteDao().insertNote(note).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());

        assertNotNull(insertedNotes);
        assertEquals(note.getContent(), insertedNotes.get(0).getContent());
        assertEquals(note.getTimestamp(), insertedNotes.get(0).getTimestamp());
        assertEquals(note.getTitle(), insertedNotes.get(0).getTitle());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note, insertedNotes.get(0));

        //update
        note.setTitle(TEST_TITLE);
        note.setTimestamp(TEST_TIMESTAMP);
        note.setContent(TEST_CONTENT);
        getNoteDao().updateNote(note).blockingGet();

        //read
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(TEST_CONTENT, insertedNotes.get(0).getContent());
        assertEquals(TEST_TIMESTAMP, insertedNotes.get(0).getTimestamp());
        assertEquals(TEST_TITLE, insertedNotes.get(0).getTitle());

        note.setId(insertedNotes.get(0).getId());
        assertEquals(note, insertedNotes.get(0));

        //delete
        getNoteDao().deleteNote(note).blockingGet();

        //confirm database is empty
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertEquals(0, insertedNotes.size());
    }

    //Insert note with null title, throw exception
    @Test(expected = SQLiteConstraintException.class)
    public void testInsertNullTitleWithException() throws Exception{

        final Note note  = new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);

        //insert
        getNoteDao().insertNote(note).blockingGet();
    }


    //Insert, Update with null title, throw exception
    @Test(expected = SQLiteConstraintException.class)
    public void insertUpdateNoteWithNullTitle() throws Exception {

        Note note = new Note(TestUtil.TEST_NOTE_1);

        //insert
        getNoteDao().insertNote(note).blockingGet();

        //read
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes());
        assertNotNull(insertedNotes);

        //update
        note = new Note(insertedNotes.get(0));
        note.setTitle(null);
        getNoteDao().updateNote(note).blockingGet();

    }

}
