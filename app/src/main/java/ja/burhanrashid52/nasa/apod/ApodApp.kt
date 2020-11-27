package ja.burhanrashid52.nasa.apod

import android.app.Application
import ja.burhanrashid52.nasa.apod.dataSource.*
import ja.burhanrashid52.nasa.apod.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class ApodApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        // Start Koin
        startKoin {
            // androidLogger()
            androidContext(this@ApodApp)
            modules(appModule, appViewModel)
        }
    }
}

val appModule = module {

    single<AssetResource> {
        createAssetResource(androidContext())
    }

    single<ApodRepository> {
        createApodRepository(get())
    }
}

val appViewModel = module {

    viewModel {
        HomeViewModel(get())
    }
}