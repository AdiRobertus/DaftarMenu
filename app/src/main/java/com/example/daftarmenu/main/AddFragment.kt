package com.example.daftarmenu.main

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fondesa.kpermissions.request.PermissionRequest
import androidx.fragment.app.Fragment
import com.example.daftarmenu.R
import com.example.daftarmenu.data.MenuDB
import com.example.daftarmenu.data.MenuMakananModel
import com.fondesa.kpermissions.extension.permissionsBuilder
import kotlinx.android.synthetic.main.tambah_fragmen.btn_simpan
import kotlinx.android.synthetic.main.tambah_fragmen.edit_Harga
import kotlinx.android.synthetic.main.tambah_fragmen.edit_menu
import kotlinx.android.synthetic.main.tambah_fragmen.ib_gambar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddFragment : Fragment(), PermissionRequest.AcceptedListener, PermissionRequest.DeniedListener {

    override fun onPermissionsAccepted(permissions: Array<out String>) {
        showMessageDialog()
    }

    override fun onPermissionsDenied(permissions: Array<out String>) {
        requstPermission()
    }

    companion object {
        fun getInstance(): AddFragment {
            return AddFragment()
        }
    }

    val GALERY = 1
    val CAMERA = 2
    var imageData: ByteArray? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tambah_fragmen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = MenuDB.getInstance(context!!)

        ib_gambar.setOnClickListener {
            checkVersion()
        }
        btn_simpan.setOnClickListener {
            simpanData(db)
            Toast.makeText(this.context,"terSimpan",Toast.LENGTH_SHORT).show()
        }
    }

    private fun simpanData(db: MenuDB?): Job {
        return GlobalScope.launch {
            db?.menuDao()?.tambahMakanan(
                MenuMakananModel(
                    namaMenu = edit_menu.text.toString(),
                    hargaMenu = edit_Harga.text.toString(),
                    gambarMenu = imageData
                )
            )
        }
    }

    fun checkVersion() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            requstPermission()
        } else {
            showMessageDialog()
        }
    }

    private fun requstPermission() {
        val request = permissionsBuilder(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).build()

        request.acceptedListener(this)
        request.deniedListener(this)

        request.send()
    }

    private fun showMessageDialog() {

        val pictureDialog = AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Silahkan Pilih")
        val pictureDialogItems = arrayOf(
            "Ambil foto dari galery",
            "Ambil foto dari kamera"
        )
        pictureDialog.setItems(pictureDialogItems) { dialog, which ->
            when (which) {
                0 -> pilihGalery()
                1 -> PilihKamera()
            }
        }
        pictureDialog.show()
    }

    private fun PilihKamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    private fun pilihGalery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media
                        .getBitmap(activity!!.contentResolver, contentURI)
                    ib_gambar.setImageBitmap(bitmap)

                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
                    imageData = stream.toByteArray()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(
                        activity, "Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            ib_gambar.setImageBitmap(thumbnail)
        }
    }
}