package com.example.suryaproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suryaproject.R
import com.example.suryaproject.model.UserEntity
import com.example.suryaproject.ui.adapter.CeleberitiesAdapter
import com.example.suryaproject.viewModels.SharedViewModel

class CeleberitiesDataFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var rv_celeberities: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var celeberitiesAdapter: CeleberitiesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view :View  = inflater.inflate(R.layout.celeberities_data_fragment,container,false)
        rv_celeberities=view.findViewById(R.id.rv_celeberities)
        linearLayoutManager= LinearLayoutManager(activity)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        viewModel.getDataFromDatabase().observe(viewLifecycleOwner, Observer<List<UserEntity>> { item ->
            // Update the UI using new item data
           setAdapter(item)
        })

        viewModel.apiResponseLivedata.observe(viewLifecycleOwner, Observer<String> {item ->
            viewModel.ShowApiResponseStatus(item)
        })
    }

    fun setAdapter(userEntity: List<UserEntity>)
    {
        if(!userEntity.isNullOrEmpty())
        {
          if(celeberitiesAdapter ==null)
          {
              celeberitiesAdapter= CeleberitiesAdapter(activity,userEntity);
              rv_celeberities.layoutManager=linearLayoutManager
              rv_celeberities.adapter=celeberitiesAdapter
          }
            else
          {
              celeberitiesAdapter.notifyDataSetChanged()
          }
        }

    }
}