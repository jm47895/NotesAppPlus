package com.jordanmadrigal.notesappplus.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jordanmadrigal.notesappplus.ui.note.NoteViewModel;
import com.jordanmadrigal.notesappplus.ui.notelist.NoteListViewModel;
import com.jordanmadrigal.notesappplus.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    public abstract ViewModel bindNoteViewModel(NoteViewModel noteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel.class)
    public abstract ViewModel bindNoteListViewModel(NoteListViewModel noteListViewModel);
}
