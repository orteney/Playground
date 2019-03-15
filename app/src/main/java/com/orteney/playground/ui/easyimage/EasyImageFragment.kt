package com.orteney.playground.ui.easyimage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.orteney.playground.R
import com.orteney.playground.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_easy_image.*
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File

class EasyImageFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_easy_image

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        cameraButton.setOnClickListener { EasyImage.openCameraForImage(this, 0) }
        galleryButton.setOnClickListener { EasyImage.openGallery(this, 0) }
        documentsButton.setOnClickListener { EasyImage.openDocuments(this, 0) }
        chooserDocumentsButton.setOnClickListener { EasyImage.openChooserWithDocuments(this, "Documents", 0) }
        chooserGalleryButton.setOnClickListener { EasyImage.openChooserWithGallery(this, "Gallery", 0) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        EasyImage.handleActivityResult(requestCode, resultCode, data, activity, object : EasyImage.Callbacks {
            override fun onImagesPicked(imageFiles: MutableList<File>, source: EasyImage.ImageSource?, type: Int) {
                val file = imageFiles.first()

                Glide.with(this@EasyImageFragment)
                    .load(file)
                    .into(imageView)
            }

            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) = Unit
            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) = Unit
        })
    }
}
