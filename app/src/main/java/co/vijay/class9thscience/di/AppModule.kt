package co.vijay.class9thscience.di

import android.content.Context
import co.vijay.class9thscience.repo.AdRepo
import co.vijay.class9thscience.repo.PdfRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePdfRepository(@ApplicationContext context: Context): PdfRepo {
        return PdfRepo(context)
    }

    @Provides
    @Singleton
    fun provideAdRepository(): AdRepo {
        return AdRepo()
    }
}