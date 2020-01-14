package rs.hc.android.di

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.hc.android.BuildConfig
import rs.hc.android.data.remote.AuthInterceptor
import rs.hc.android.data.remote.GithubAPI
import rs.hc.android.data.repository.AppRepository
import rs.hc.android.data.repository.AppRepositoryImpl
import rs.hc.android.domain.RepoUseCase
import rs.hc.android.domain.UserDetailsUseCase
import rs.hc.android.mapper.DataModelMapper
import rs.hc.android.ui.commitdetails.viewmodel.CommitsViewModel
import rs.hc.android.ui.userdetails.viewmodel.UserDetailsViewModel
import rs.hc.android.ui.userrepos.viewmodel.UserReposViewModel

val networkModule = module {

    single { provideRetrofit(get()) }

    factory { AuthInterceptor() }

    factory { provideOkHttpClient(get()) }

    factory { provideGithubApi(get()) }
}

val hcModule = module {

    single { AppRepositoryImpl(get()) as AppRepository }

    single { DataModelMapper() }

    //region use case
    factory { UserDetailsUseCase(get()) }

    factory { RepoUseCase(get()) }
    //endregion

    //region view model
    viewModel { UserDetailsViewModel(get(), get()) }

    viewModel { UserReposViewModel(get(), get()) }

    viewModel { CommitsViewModel(get(), get()) }
    //endregion
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideGithubApi(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)
