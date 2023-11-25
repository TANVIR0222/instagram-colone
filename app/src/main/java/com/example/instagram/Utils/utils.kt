package com.example.instagram.Utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImage(uri: Uri, folderName: String, callback: (String) -> Unit) {

    var ImageUrl: String? = null

    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                ImageUrl = it.toString()
                callback(ImageUrl!!)
            }
        }

}

fun uploadReel(
    uri: Uri,
    folderName: String,
    progressDialog: ProgressDialog,
    callback: (String) -> Unit
) {

    var ImageUrl: String? = null
    // loading time fun
    progressDialog.setTitle("uploading......")
    progressDialog.show()

    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                ImageUrl = it.toString()
                progressDialog.dismiss()
                callback(ImageUrl!!)
            }
        }
        // upload video time
        .addOnProgressListener {

            var uploadedValue: Long = (it.bytesTransferred / it.totalByteCount)*100
            progressDialog.setMessage("Uploaded $uploadedValue %")


        }

}