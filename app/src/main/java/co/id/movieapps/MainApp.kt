package co.id.movieapps

import android.app.Application
import co.id.movieapps.di.myAppModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)
            modules(myAppModule)
        }
    }
}