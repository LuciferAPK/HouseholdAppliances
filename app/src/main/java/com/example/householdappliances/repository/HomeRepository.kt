package com.example.householdappliances.repository

//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.cryptounderground.BuildConfig
//import com.example.cryptounderground.data.model.CoinResponse
//import com.example.cryptounderground.network.Api
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//class HomeRepository @Inject constructor(
//    private val api: Api
//) {
//    private val TAG = HomeRepository::class.simpleName
//    private val coinsLiveData = MutableLiveData<Result<CoinResponse>>()
//    fun getAllListCoin(url: String?): LiveData<Result<CoinResponse>> {
//        CoroutineScope(Dispatchers.IO).launch {
//            val request = api.getListCoin(url = url, CMC_PRO_API_KEY = BuildConfig.CMC_PRO_API_KEY)
//            withContext(Dispatchers.Main) {
//                if (request.isSuccessful) {
//                    val coinsItemResponse = Result.Success(request.body() as CoinResponse)
//                    coinsLiveData.postValue(coinsItemResponse)
//                } else {
//                    Log.d(TAG, "getAllListCoin: Call Api Failed")
//                }
//            }
//        }
//        return coinsLiveData
//    }
//}