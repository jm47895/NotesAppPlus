package com.jordanmadrigal.notesappplus.di;

import androidx.lifecycle.ViewModelProvider;

import com.jordanmadrigal.notesappplus.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
