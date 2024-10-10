package co.vijay.class9thscience.repo

import android.app.Activity
import android.util.Log
import co.vijay.class9thscience.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import javax.inject.Inject

class AdRepo @Inject constructor() {

    private var interstitialAd: InterstitialAd? = null
    private val adUnitId = BuildConfig.INTERSTITIAL_AD_UNIT_ID

    fun loadAd(activity: Activity) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(activity, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("AdRepository", "Ad failed to load: ${adError.message}")
                interstitialAd = null
            }

            override fun onAdLoaded(loadedAd: InterstitialAd) {
                Log.d("AdRepository", "Ad was loaded.")
                interstitialAd = loadedAd
            }
        })
    }

    fun showAd(activity: Activity) {
        interstitialAd?.let { ad ->
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("AdRepository", "Ad dismissed.")
                    interstitialAd = null
                    loadAd(activity)
                }

                override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {
                    Log.d("AdRepository", "Ad failed to show.")
                    interstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("AdRepository", "Ad showed full-screen content.")
                    interstitialAd = null
                }
            }
            ad.show(activity)
        } ?: Log.d("AdRepository", "Interstitial ad is not ready yet.")
    }
}