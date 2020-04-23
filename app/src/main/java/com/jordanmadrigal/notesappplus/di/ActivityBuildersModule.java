package com.jordanmadrigal.notesappplus.di;

import com.jordanmadrigal.notesappplus.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();
}
