package com.cindodcindy.vieroshoesadminnew

import android.app.Application
import android.content.Context
import com.cindodcindy.vieroshoesadminnew.view.utils.TrackGps
import com.orhanobut.hawk.Hawk

class MainApplication : Application() {

    var trackGps: TrackGps? = null

    companion object {
        var latitude = 0.0
        var longitude = 0.0
    }

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this)
            .build()

        callCurrentLoc(this)
    }

    fun callCurrentLoc(context: Context) {
        trackGps = TrackGps(context)
        if (trackGps!!.canGetLocation()) {
            latitude = trackGps!!.latitude
            longitude = trackGps!!.longitude
        }
    }
}