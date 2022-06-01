package com.sk.moviefinder.di.component

import com.sk.moviefinder.MovieFinderApplication
import com.sk.moviefinder.data.di.module.ApiModule
import com.sk.moviefinder.data.di.module.RepositoryModule
import com.sk.moviefinder.di.module.*
import com.sk.moviefinder.domain.di.module.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

//@Component(modules = [ApiModule::class, ApplicationModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class,
        ApiModule::class, ApplicationModule::class, RepositoryModule::class, UseCaseModule::class]
)
interface MovieFinderMainComponent : AndroidInjector<MovieFinderApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MovieFinderApplication): MovieFinderMainComponent
    }

}