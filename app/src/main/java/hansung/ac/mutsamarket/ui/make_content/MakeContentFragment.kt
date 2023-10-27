package hansung.ac.mutsamarket.ui.make_content

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hansung.ac.mutsamarket.R
import hansung.ac.mutsamarket.databinding.FragmentMakeContentBinding
import kotlin.math.log
import androidx.navigation.fragment.findNavController

class MakeContentFragment : Fragment() {

    private var _binding: FragmentMakeContentBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val REQUEST_IMAGE_PICK = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //사진추가하는 코드+페이지 이동 코드
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val writeButton=view.findViewById<Button>(R.id.write_button) //등록하기
        val picButton = view.findViewById<Button>(R.id.pic_button) //사진추가
        picButton.setOnClickListener { //사진추가 이벤트
            openGallery()
        }

        writeButton.setOnClickListener{//등록하기 이벤트
            val navController = findNavController()
            navController.navigate(R.id.action_navigation_make_content_to_navigation_home)
       }
    }


    val REQ_GALLERY = 1
    fun openGallery() { //갤러리열기
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri = data.data!!

            // 선택한 이미지를 ImageView에 설정
            binding.imageView.setImageURI(selectedImageUri)
        }
    }


}

