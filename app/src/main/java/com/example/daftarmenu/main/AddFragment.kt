package com.example.daftarmenu.main

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fondesa.kpermissions.request.PermissionRequest
import androidx.fragment.app.Fragment
import com.example.daftarmenu.R
import com.fondesa.kpermissions.extension.permissionsBuilder
import kotlinx.android.synthetic.main.tambah_fragmen.ib_gambar

class AddFragment : Fragment(), PermissionRequest.AcceptedListener, PermissionRequest.DeniedListener {

    override fun onPermissionsAccepted(permissions: Array<out String>) {
    }

    override fun onPermissionsDenied(permissions: Array<out String>) {
    }

    companion object {
        fun getInstance(): AddFragment {
            return AddFragment()
        }
    }

    val GALERY=1
    val  CAMERA=2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tambah_fragmen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ib_gambar.setOnClickListener {
            checkVersion()
        }
    }

    fun checkVersion() {
        if (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.M) {
            requstPermission()
        } else {
            showMessageDialog()
        }
    }

    private fun requstPermission() {
        val request = permissionsBuilder(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).build()

        request.acceptedListener(this)
        request.deniedListener(this)

        request.send()
    }

    private fun showMessageDialog() {

        val  pictureDialog = AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Silahkan Pilih")
        val pictureDialogItems = arrayOf(
            "Ambil foto dari galery",
            "Ambil foto dari kamera"
        )
        pictureDialog.setItems(pictureDialogItems){dialog, which ->
            when(which){
                0 -> pilihGalery()
                1 -> PilihKamera()
            }
        }
    }

    private fun PilihKamera() {

    }

    private fun pilihGalery() {

    }
}