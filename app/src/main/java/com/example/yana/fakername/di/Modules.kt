package com.example.yana.fakername.di

import androidx.room.Room
import com.example.yana.fakername.db.FakerAppDataBase
import com.example.yana.fakername.fragmentsViewModel.*
import com.example.yana.fakername.iteractor.*
import com.example.yana.fakername.network.*
import com.example.yana.fakername.repository.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


val fakerModules by lazy {
    loadKoinModules(
        listOf(
            networkModule,
            viewModelModule,
            iteractorModules,
            dbModule,
//            utilsModule,
            repositoryModel
        )
    )
}

val networkModule = module {
    single {
        RetrofitFaker.buildRetrofit()
    }
}
val viewModelModule = module {
    viewModel { MainViewModel(get(), get(),get()) }
    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { DataAddViewModel(get(), get(), get()) }
    viewModel { PrivateCabinetViewModel(get(), get(),get()) }
    viewModel { AddTextViewModel(get(), get()) }
    viewModel { RegisterNameViewModel(get(), get()) }
    viewModel { AboutViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }

}
val iteractorModules = module {
    single<FakerIteractor> { FakerIteractorImpl(get()) }
    single<AuthIteractor> { AuthIteractorImpl(get()) }
    single<DocumentIteractor> { DocumentIteractorImpl(get()) }
    single<ProfileCabinetIteractor> { ProfileCabinetIteractorImpl(get()) }
    single<CreateCommentIteractor> { CreateCommentIteractorImpl(get()) }
    single<SearchIteractor> { SearchIteractorImpl(get()) }
}
val repositoryModel = module {
    single <FakerRepository> { FakerRepositoryImpl(get(), get()) }
    single <AuthRepository> { AuthRepositoryImpl(get()) }
    single <DocumentRepository> { DocumentRepositoryImpl(get(), get()) }
    single <ProfileCabinetRepository> { ProfileCabinetRepositoryImpl(get()) }
    single <CreateCommentRepository> { CreateCommentRepositoryImpl(get()) }
    single <SearchRepository> { SearchRepositoryImpl(get()) }
}
val dbModule = module {
    single { Room.databaseBuilder(get(), FakerAppDataBase::class.java,"fakerName")
        .allowMainThreadQueries()
        .build()
    }
    single { get<FakerAppDataBase>().getFakerDao()}
}