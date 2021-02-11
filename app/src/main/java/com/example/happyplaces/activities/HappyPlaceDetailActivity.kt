package com.example.happyplaces.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.happyplaces.R
import com.example.happyplaces.models.HappyPlaceModel

class HappyPlaceDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happy_place_detail)

        var happyPlaceDetailModel: HappyPlaceModel? = null

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            happyPlaceDetailModel =
                intent.getParcelableExtra(MainActivity.EXTRA_PLACE_DETAILS)
        }

        if (happyPlaceDetailModel != null) {
            val toolbarHappyPlaceDetail = findViewById<Toolbar>(R.id.toolBarHappyPlaceDetail)
            val ivPlaceImage = findViewById<ImageView>(R.id.iv_place_image)
            val tvDescription = findViewById<TextView>(R.id.tv_description)
            val tvLocation = findViewById<TextView>(R.id.tv_location)
            val btnViewOnMap = findViewById<TextView>(R.id.btn_view_on_map)

            setSupportActionBar(toolbarHappyPlaceDetail)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetailModel.title

            toolbarHappyPlaceDetail.setNavigationOnClickListener {
                onBackPressed()
            }

            ivPlaceImage.setImageURI(Uri.parse(happyPlaceDetailModel.image))
            tvDescription.text = happyPlaceDetailModel.description
            tvLocation.text = happyPlaceDetailModel.location

            btnViewOnMap.setOnClickListener {
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, happyPlaceDetailModel)
                startActivity(intent)
            }
        }
    }
}