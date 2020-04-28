package com.jordanmadrigal.notesappplus.repository;

import androidx.annotation.NonNull;
import androidx.room.Insert;

import com.jordanmadrigal.notesappplus.persistence.NoteDao;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class NoteRepository {

    @NonNull
    private final NoteDao noteDao;

    @Inject
    public NoteRepository(@NonNull NoteDao noteDao){
        this.noteDao = noteDao;
    }

}
