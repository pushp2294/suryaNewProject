package com.example.suryaproject.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.suryaproject.BaseApplication
import com.example.suryaproject.R
import com.example.suryaproject.databinding.EmailFragmentBinding
import com.example.suryaproject.viewModels.SharedViewModel

class EmailFragment : Fragment() {
    lateinit var sharedViewModel: SharedViewModel
    var str_email: String = ""
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        val binding: EmailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.email_fragment, container, false)
        binding.viewmodel = sharedViewModel
        val sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("SuryaDataBase", Context.MODE_PRIVATE)
        str_email = sharedPreferences.getString("email", "").toString();
        sharedViewModel.sendEmail(str_email)
        return binding.root
    }
}
