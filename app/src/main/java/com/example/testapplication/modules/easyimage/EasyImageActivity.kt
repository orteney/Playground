package com.example.testapplication.modules.easyimage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.testapplication.R
import kotlinx.android.synthetic.main.activity_easy_image.*
import org.jetbrains.anko.sdk19.listeners.onClick
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.lang.Exception


class EasyImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_image)

        initViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object : EasyImage.Callbacks {
            override fun onImagesPicked(imageFiles: MutableList<File>, source: EasyImage.ImageSource?, type: Int) {
                val file = imageFiles.first()

                Glide.with(this@EasyImageActivity)
                    .load(file)
                    .into(imageView)
            }

            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) = Unit
            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) = Unit
        })
    }

    private fun initViews() {
        cameraButton.onClick { EasyImage.openCamera(this, 0) }
        galleryButton.onClick { EasyImage.openGallery(this, 0) }
        documentsButton.onClick { EasyImage.openDocuments(this, 0) }
        chooserDocumentsButton.onClick { EasyImage.openChooserWithDocuments(this, "Documents", 0) }
        chooserGalleryButton.onClick { EasyImage.openChooserWithGallery(this, "Gallery", 0) }
    }
}