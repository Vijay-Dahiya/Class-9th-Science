package co.vijay.class9thscience.adCenter

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun showInterstitialAd(activity: Activity) {
    //ca-app-pub-4619452986341134/3178303115  -- original Id
    // ca-app-pub-3940256099942544/1033173712  -- test id
    val originalId = "ca-app-pub-4619452986341134/3178303115"
    val testId = "ca-app-pub-3940256099942544/1033173712"

     var mInterstitialAd: InterstitialAd? = null
     val TAG = "MainActivity"

    val adRequest = AdRequest.Builder().build()

    InterstitialAd.load(activity,testId, adRequest, object : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad(adError: LoadAdError) {
            adError.toString().let { Log.d(TAG, it) }
            mInterstitialAd = null
        }

        override fun onAdLoaded(interstitialAd: InterstitialAd) {
            Log.d(TAG, "Ad was loaded.")
            mInterstitialAd = interstitialAd
            CoroutineScope(Dispatchers.Main).launch {
                mInterstitialAd?.show(activity)
            }
        }
    })


    mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
        override fun onAdClicked() {
            // Called when a click is recorded for an ad.
            Log.d(TAG, "Ad was clicked.")
        }

        override fun onAdDismissedFullScreenContent() {
            // Called when ad is dismissed.
            Log.d(TAG, "Ad dismissed fullscreen content.")
            mInterstitialAd = null
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            // Called when ad fails to show.
            Log.e(TAG, "Ad failed to show fullscreen content.")
            mInterstitialAd = null
        }

        override fun onAdImpression() {
            // Called when an impression is recorded for an ad.
            Log.d(TAG, "Ad recorded an impression.")
        }

        override fun onAdShowedFullScreenContent() {
            // Called when ad is shown.
            Log.d(TAG, "Ad showed fullscreen content.")
        }
    }

}