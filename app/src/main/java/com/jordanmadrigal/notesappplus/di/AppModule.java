package com.jordanmadrigal.notesappplus.di;

import android.app.Application;

import androidx.room.Room;

import com.jordanmadrigal.notesappplus.persistence.NoteDao;
import com.jordanmadrigal.notesappplus.persistence.NoteDatabase;
import com.jordanmadrigal.notesappplus.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.jordanmadrigal.notesappplus.persistence.NoteDatabase.DATABASE_NAME;

@Module
class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(application, NoteDatabase.class, DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase){
        return noteDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }

}
