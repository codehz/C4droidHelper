package codehz.c4droidhelper

import android.app.Application
import com.kii.cloud.storage.Kii

Used
public class ThisApplication : Application() {
    companion object {
        public var self: ThisApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        self = this
        Kii.initialize(this, "43c75dd2", "e37afda64d302b9099e0fb460bdafe19", Kii.Site.CN)
    }
}
