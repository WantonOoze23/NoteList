package com.tyshko.notelist

import android.app.Application
import androidx.room.Room
import com.tyshko.notelist.dao.NoteDatabase
import com.tyshko.notelist.data.NoteDao
import com.tyshko.notelist.repository.NoteRepository
import com.tyshko.notelist.repository.NoteRepositoryImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Component(modules = [AppModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {
    fun inject(application: Application)
    fun inject(activity: MainActivity)
}

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(application, NoteDatabase::class.java, "note_db").build()
    }

    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao = database.dao()
}

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
}