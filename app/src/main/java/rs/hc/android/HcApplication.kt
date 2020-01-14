package rs.hc.android

import android.app.Application
import org.koin.core.context.startKoin
import rs.hc.android.di.hcModule
import rs.hc.android.di.networkModule

class HcApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            modules(networkModule)
            modules(hcModule)
        }
    }
}