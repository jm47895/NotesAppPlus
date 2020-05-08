package com.jordanmadrigal.notesappplus.ui.noteslist;

import androidx.lifecycle.MutableLiveData;

import com.jordanmadrigal.notesappplus.models.Note;
import com.jordanmadrigal.notesappplus.repository.NoteRepository;
import com.jordanmadrigal.notesappplus.ui.Resource;
import com.jordanmadrigal.notesappplus.ui.notelist.NotesListViewModel;
import com.jordanmadrigal.notesappplus.util.InstantExecutorExtension;
import com.jordanmadrigal.notesappplus.util.LiveDataTestUtil;
import com.jordanmadrigal.notesappplus.util.TestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.jordanmadrigal.notesappplus.repository.NoteRepository.DELETE_FAILURE;
import static com.jordanmadrigal.notesappplus.repository.NoteRepository.DELETE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(InstantExecutorExtension.class)
public class NotesListViewModelTest {

    private NotesListViewModel viewModel;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        viewModel = new NotesListViewModel(noteRepository);
    }

    //Retrieve a list of notes, observe list, return list

    @Test
    void retrieveNotes_returnNoteList() throws Exception {
        //Arrange
        List<Note> returnedData = TestUtil.TEST_NOTES_LIST;
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.getNotes()).thenReturn(returnedValue);
        //Act
        viewModel.getNotes();
        List<Note> observedData = liveDataTestUtil.getValue(viewModel.observeNotes());
        //Assert
        assertEquals(returnedData, observedData);
    }


    //Retrieve a list of notes, observe list, return empty list

    @Test
    void retrieveNotes_returnEmptyList() throws Exception {
        //Arrange
        List<Note> returnedData = new ArrayList<>();
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.getNotes()).thenReturn(returnedValue);
        //Act
        viewModel.getNotes();
        List<Note> observedData = liveDataTestUtil.getValue(viewModel.observeNotes());
        //Assert
        assertEquals(returnedData, observedData);
    }


    //Delete note, observe Resource.success, return Resource.success

    @Test
    void deleteNote_observerResourceSuccess() throws Exception {
        //Arrange
        Note deletedNote = new Note(TestUtil.TEST_NOTE_1);
        Resource<Integer> returnedData = Resource.success(1, DELETE_SUCCESS);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<Resource<Integer>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.deleteNote(any(Note.class))).thenReturn(returnedValue);
        //Act
        Resource<Integer> observedValue = liveDataTestUtil.getValue(viewModel.deleteNote(deletedNote));
        //Assert
        assertEquals(returnedData, observedValue);
    }


    //Delete note, observe Resource.error, return Resource.error
    @Test
    void deleteNote_observerResourceError() throws Exception {
        //Arrange
        Note deletedNote = new Note(TestUtil.TEST_NOTE_1);
        Resource<Integer> returnedData = Resource.error(null, DELETE_FAILURE);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<Resource<Integer>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.deleteNote(any(Note.class))).thenReturn(returnedValue);
        //Act
        Resource<Integer> observedValue = liveDataTestUtil.getValue(viewModel.deleteNote(deletedNote));
        //Assert
        assertEquals(returnedData, observedValue);
    }
}
