//package com.example.cryptounderground.ui.screen.main
//
//import androidx.lifecycle.ViewModel
//import com.example.cryptounderground.base.Result
//import com.example.cryptounderground.base.SingleLiveEvent
//import com.example.cryptounderground.data.model.CoinResponse
////import com.example.cryptounderground.data.model.UrlsRetry
//import com.example.cryptounderground.network.END_POINT_GET_WALLPAPERS
////import com.example.cryptounderground.network.generateUrlRetry
//import com.example.cryptounderground.repository.HomeRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class MainViewModel @Inject constructor(
//    private val homeRepository: HomeRepository
//): ViewModel() {
//
//    val mLiveData = SingleLiveEvent<Result<CoinResponse>>()
//    fun getWallpapers(
//        urlsRetry: String = END_POINT_GET_WALLPAPERS
//    ) {
//        val request = homeRepository.getAllListCoin(
//            urlsRetry
//        )
//        mLiveData.addSource(request) {
//            mLiveData.postValue(it)
//        }
//    }
//}