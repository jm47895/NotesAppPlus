package com.jordanmadrigal.notesappplus.di;

import com.jordanmadrigal.notesappplus.ui.note.NoteActivity;
import com.jordanmadrigal.notesappplus.ui.notelist.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();

    @ContributesAndroidInjector
    abstract NoteActivity contributeNoteActivity();
}
