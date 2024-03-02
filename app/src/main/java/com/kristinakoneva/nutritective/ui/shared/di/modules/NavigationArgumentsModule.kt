package com.kristinakoneva.nutritective.ui.shared.di.modules

import androidx.lifecycle.SavedStateHandle
import com.kristinakoneva.nutritective.ui.shared.constants.NavArg
import com.kristinakoneva.nutritective.ui.shared.di.qualifiers.TextArgument
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class NavigationArgumentsModule {

  @Provides
  @ViewModelScoped
  @TextArgument
    fun provideNavigationArguments(savedStateHandle: SavedStateHandle): String {
        return checkNotNull(savedStateHandle[NavArg.ARGUMENT_TEXT])
    }
}