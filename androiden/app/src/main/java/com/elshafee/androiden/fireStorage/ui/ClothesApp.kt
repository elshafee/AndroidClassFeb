package com.elshafee.androiden.fireStorage.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androiden.databinding.ActivityClothesAppBinding
import com.elshafee.androiden.fireStorage.uitls.ClothesImageAdapter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val REQUEST_CODE_IMAGE_PICKER = 0

class ClothesApp : AppCompatActivity() {
    var currentImage: Uri? = null
    val imageRefrence = Firebase.storage.reference
    lateinit var binding: ActivityClothesAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothesAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listAllImagesFromStorage()
        binding.ivClothesImage.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
            }
        }

        binding.btnUploadImage.setOnClickListener {
            uploadImage("myImage")
        }

        binding.btnDownloadImage.setOnClickListener {
            downloadImageFromStorage("myImage")
        }

        binding.btnDeleteImage.setOnClickListener {
            deleteImageFromStorage("myImage")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_IMAGE_PICKER) {
            data?.data.let {
                currentImage = it
                binding.ivClothesImage.setImageURI(it)
            }
        }
    }

    private fun uploadImage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                currentImage?.let {
                    imageRefrence.child("images/$fileName").putFile(it).await()

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ClothesApp,
                            "Image Uploaded Successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothesApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun downloadImageFromStorage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val maxDownloadSizeByte = 5L * 1024 * 1024
                val imageByte =
                    imageRefrence.child("images/$fileName").getBytes(maxDownloadSizeByte).await()
                val imageBitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                withContext(Dispatchers.Main) {
                    binding.ivClothesImage.setImageBitmap(imageBitmap)
                    Toast.makeText(
                        this@ClothesApp,
                        "Image Downloaded Successfully",
                        Toast.LENGTH_LONG
                    ).show()

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothesApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun deleteImageFromStorage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val maxDownloadSizeByte = 5L * 1024 * 1024
                val imageByte =
                    imageRefrence.child("images/$fileName").delete().await()

                withContext(Dispatchers.Main) {

                    Toast.makeText(this@ClothesApp, "Image Deleted Successfully", Toast.LENGTH_LONG)
                        .show()

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ClothesApp, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun listAllImagesFromStorage() {
        CoroutineScope(Dispatchers.IO).launch {
            val allImages = imageRefrence.child("images/").listAll().await()
            val imagesUrls = mutableListOf<String>()
            for (singleImages in allImages.items) {
                val singleImageUrl = singleImages.downloadUrl.await()
                imagesUrls.add(singleImageUrl.toString())
            }

            withContext(Dispatchers.Main) {
                try {
                    val clothesImageAdapter = ClothesImageAdapter(imagesUrls)
                    binding.rvClothesImages.apply {
                        adapter = clothesImageAdapter
                        layoutManager = LinearLayoutManager(this@ClothesApp)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ClothesApp, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}