package com.example.a3_recyclerview

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.a3_recyclerview.databinding.ActivityDetailBinding
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        title = "Notifications"

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar.navigationIcon?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
            } else {
                setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
            }
        }

        val intent = intent
        val aTitle = intent.getStringExtra("iTitle")
        val aDesc = intent.getStringExtra("iDesc")
        val aYears = intent.getStringExtra("iYears")
        val aSex = intent.getStringExtra("iSex")
        val aImageView = intent.getIntExtra("iImageView", 0)

        supportActionBar?.title = aTitle
        binding.detailUserNameTextView.text = aTitle
        binding.detailDescTextView.text = aDesc
        binding.detailLiveYearsTextView.text = aYears
        binding.detailSexUserTextView.text = aSex
        binding.detailPhotoImageView.setImageResource(aImageView)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}