package com.refin.aplikasikelompok

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.refin.aplikasikelompok.databinding.FragmentProfilBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profil.*
import java.io.ByteArrayOutputStream

class ProfilFragment : Fragment() {

    private var binding1 : FragmentProfilBinding? = null
    lateinit var auth : FirebaseAuth
    private lateinit var imgUri : Uri

    private val binding get() = binding1!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding1 = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding1 = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            if (user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(ci_user)
            } else {
                Picasso.get().load(R.drawable.icon_user).into(ci_user)
            }

            binding.edtUsername.setText(user.displayName)
            binding.edtEmail.setText(user.email)
        }

        binding.ciUser.setOnClickListener {
            camera()
        }

        binding.btnSave.setOnClickListener {
            val image = when {
                ::imgUri.isInitialized -> imgUri
                user?.photoUrl == null -> Uri.parse("R.drawable.icon_user")
                else -> user.photoUrl
            }

            val username = binding.edtUsername.text.toString().trim()

            if (username.isEmpty()){
                binding.edtUsername.error = "Username harus diisi"
                binding.edtUsername.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(activity, "Profile Telah di Update", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        binding.btnLogout.setOnClickListener {
            btn_logout()
        }
    }

    private fun camera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent?.resolveActivity(it).also {
                    startActivityForResult(intent, REQ_CAM)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAM && resultCode == RESULT_OK) {
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImg(imgBitmap)
        }
    }

    private fun uploadImg(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img_user/${FirebaseAuth.getInstance().currentUser?.email}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val img = baos.toByteArray()
        ref.putBytes(img)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener{ Task ->
                        Task.result.let { Uri ->
                            imgUri = Uri
                            binding.ciUser.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
    }

    private fun btn_logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        const val REQ_CAM = 100
    }
}