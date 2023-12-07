package com.pinto.githubuser.presentation.screen.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pinto.githubuser.R
import com.pinto.githubuser.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val bundle: Bundle? = intent.extras
        val itemId = bundle?.getInt("ITEM_ID", -1)
        val itemName = bundle?.getString("ITEM_NAME", "")
        val itemFullname = bundle?.getString("ITEM_FULLNAME", "")
        val itemDescription = bundle?.getString("ITEM_DESCRIPTION", "")
        val itemURL = bundle?.getString("ITEM_URL", "")
        val itemOwner = bundle?.getString("ITEM_OWNER", "")
        val imageUrl = bundle?.getString("ITEM_IMAGE_URL", "")

        title = itemName

        binding.txtDetailName.text = itemName
        binding.txtDetailFullname.text = itemFullname
        binding.txtDetailDescription.text = itemDescription
        binding.txtDetailUrl.text = itemURL
        binding.txtDetailOwner.text = itemOwner

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.detailImage)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Or perform any action you want when the up button is clicked
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}