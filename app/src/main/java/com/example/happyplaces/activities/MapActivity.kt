package com.example.happyplaces.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.happyplaces.R
import com.example.happyplaces.models.HappyPlaceModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mHappyPlaceDetails: HappyPlaceModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val toolbarMap = findViewById<Toolbar>(R.id.toolBarMap)

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            mHappyPlaceDetails = intent.getParcelableExtra(MainActivity.EXTRA_PLACE_DETAILS)
        }

        if (mHappyPlaceDetails != null) {
            setSupportActionBar(toolbarMap)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = mHappyPlaceDetails!!.title

            toolbarMap.setNavigationOnClickListener {
                onBackPressed()
            }

            val supportMapFragment: SupportMapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            supportMapFragment.getMapAsync(this)

        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val position = LatLng(mHappyPlaceDetails!!.latitude, mHappyPlaceDetails!!.longitude)
        googleMap!!.addMarker(
            MarkerOptions().position(position).title(mHappyPlaceDetails!!.location)
        )
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(position, 10f)
        googleMap.animateCamera(newLatLngZoom)
    }
}