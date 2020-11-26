package ja.burhanrashid52.nasa.apod

import android.app.Application
import ja.burhanrashid52.nasa.apod.dataSource.AssetResource
import ja.burhanrashid52.nasa.apod.dataSource.createApodRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
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
            modules(appModule)
        }
    }
}

val appModule = module {

    single {
        AssetResource(androidContext())
    }

    single {
        createApodRepository(get())
    }
}