package com.example.suryaproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryaproject.BR;
import com.example.suryaproject.R;
import com.example.suryaproject.databinding.AdapterCeleberitiesBinding;
import com.example.suryaproject.model.UserEntity;

import java.util.List;

public class CeleberitiesAdapter extends RecyclerView.Adapter<CeleberitiesAdapter.MyHandler> {
    List<UserEntity> userEntityList;
    Context context;

    public CeleberitiesAdapter(Context context,List<UserEntity> userEntityList) {
        this.context=context;
        this.userEntityList=userEntityList;
    }

    @NonNull
    @Override
    public MyHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterCeleberitiesBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_celeberities, parent, false);

        return new MyHandler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHandler holder, int position) {
        UserEntity userEntity = userEntityList.get(position);
        holder.bind(userEntity);
    }

    @Override
    public int getItemCount() {
        return userEntityList.size();
    }

    public class MyHandler extends RecyclerView.ViewHolder {
        public AdapterCeleberitiesBinding itemRowBinding;

        public MyHandler(@NonNull AdapterCeleberitiesBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }
        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.celeberitiesdata, obj);
            itemRowBinding.executePendingBindings();
        }
    }
}
