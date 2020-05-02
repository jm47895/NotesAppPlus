package com.jordanmadrigal.notesappplus.repository;

import com.jordanmadrigal.notesappplus.models.Note;
import com.jordanmadrigal.notesappplus.persistence.NoteDao;
import com.jordanmadrigal.notesappplus.ui.Resource;
import com.jordanmadrigal.notesappplus.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Singleton;

import io.reactivex.Single;

import static com.jordanmadrigal.notesappplus.repository.NoteRepository.INSERT_FAILURE;
import static com.jordanmadrigal.notesappplus.repository.NoteRepository.INSERT_SUCCESS;
import static com.jordanmadrigal.notesappplus.repository.NoteRepository.NOTE_TITLE_NULL;
import static com.jordanmadrigal.notesappplus.repository.NoteRepository.UPDATE_FAILURE;
import static com.jordanmadrigal.notesappplus.repository.NoteRepository.UPDATE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NoteRepositoryTest {

    private static final Note testNote = new Note(TestUtil.TEST_NOTE_1);

    private NoteRepository noteRepository;

    private NoteDao noteDao;

    @BeforeEach
    public void initEach(){
        noteDao = mock(NoteDao.class);
        noteRepository = new NoteRepository(noteDao);
    }

    //Insert note, verify the correct method is called, confirm observer is triggered, confirm new rows inserted

    @Test
    void insertRow_returnRow() throws Exception {
        //Arrange
        final Long insertedRow = 1L;
        final Single<Long> returnedData = Single.just(insertedRow);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(testNote).blockingFirst();

        //Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        System.out.println(returnedValue);
        assertEquals(Resource.success(1, INSERT_SUCCESS), returnedValue);

        /*//Rxjava style test
        noteRepository.insertNote(testNote)
                .test()
                .await()
                .assertValue(Resource.success(1, INSERT_SUCCESS));*/

    }


    //Insert note, Failure (-1)

    @Test
    void insertNote_returnFailure() throws Exception {
        //Arrange
        final Long failedInsert = -1L;
        final Single<Long> returnedData = Single.just(failedInsert);
        when(noteDao.insertNote(any(Note.class))).thenReturn(returnedData);

        //Act
        final Resource<Integer> returnedValue = noteRepository.insertNote(testNote).blockingFirst();

        //Assert
        verify(noteDao).insertNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, INSERT_FAILURE), returnedValue);

    }


    //Insert note, null title, confirm exception thrown

    @Test
    void insertNote_nullTitle_throwException() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Note note = new Note(testNote);
                note.setTitle(null);
                noteRepository.insertNote(note);
            }
        });

        assertEquals(NOTE_TITLE_NULL, exception.getMessage());
    }

    //Update note,
    // verify the correct method is called,
    // confirm observer is triggered, confirm new rows updated

    @Test
    void updateNote_returnNumRow() throws Exception {
        //Arrange
        final int updatedRow = 1;
        when(noteDao.updateNote(any(Note.class))).thenReturn(Single.just(updatedRow));
        //Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(testNote).blockingFirst();
        //Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.success(1, UPDATE_SUCCESS), returnedValue);
    }


    //Update note, Failure (-1)

    @Test
    void updateNote_returnFailure() throws Exception {
        //Arrange
        final int failedInsert = -1;
        final Single<Integer> returnedData = Single.just(failedInsert);
        when(noteDao.updateNote(any(Note.class))).thenReturn(returnedData);
        //Act
        final Resource<Integer> returnedValue = noteRepository.updateNote(testNote).blockingFirst();
        //Assert
        verify(noteDao).updateNote(any(Note.class));
        verifyNoMoreInteractions(noteDao);

        assertEquals(Resource.error(null, UPDATE_FAILURE), returnedValue);
    }


    //Insert note, null title, confirm exception thrown
    @Test
    void updateNote_nullTitle_throwException() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Note note = new Note(testNote);
                note.setTitle(null);
                noteRepository.updateNote(note);
            }
        });

        assertEquals(NOTE_TITLE_NULL, exception.getMessage());
    }
    
}
