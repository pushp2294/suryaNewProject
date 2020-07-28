package com.example.suryaproject.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suryaproject.BaseApplication
import com.example.suryaproject.model.ApiCallBusinessLogic
import com.example.suryaproject.model.UserEntity
import com.example.suryaproject.roomdatabase.RoomDatabaseForCountries
import com.example.suryaproject.util.Constants

class SharedViewModel : ViewModel() {
    val apiResponseLivedata = MutableLiveData<String>()
    var str_email: String = "";
    private var apiCallBusinessLogic: ApiCallBusinessLogic = ApiCallBusinessLogic()

    fun sendEmail(item: String): String {
        if (!item.isNullOrEmpty()) {
            if (Constants.isNetworkAvailable(BaseApplication.getAppContext())) {
                apiCallBusinessLogic.getCeleberitiesDetails(item, apiResponseLivedata)
                apiResponseLivedata.value = item;
            } else {
                Toast.makeText(BaseApplication.getAppContext(), "Please check network for updating data.", Toast.LENGTH_SHORT).show()
            }
        }
        return ""
    }

    fun onSubmitButtonClick() {
        if (!str_email.isNullOrEmpty()) {
            val sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("SuryaDataBase", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("email", str_email);
            editor.apply()
            sendEmail(str_email);
        } else {
            Toast.makeText(BaseApplication.getAppContext(), "Please enter the email", Toast.LENGTH_SHORT).show()
        }
    }

    fun ShowApiResponseStatus(status: String) {
        if (status.equals("success")) {
            Toast.makeText(BaseApplication.getAppContext(), "Data updated Successfull.", Toast.LENGTH_LONG).show()
        } else if (status.equals("error")) {
            Toast.makeText(BaseApplication.getAppContext(), "Something went wrong while updating data.", Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromDatabase(): LiveData<List<UserEntity>> {
        return RoomDatabaseForCountries.providesRoomDatabase(BaseApplication.getAppContext()).getPromptReminderDaoInstance().getAllList()
    }

}