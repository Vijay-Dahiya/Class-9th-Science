package co.vijay.class9thscience.viewmodel

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.vijay.class9thscience.repo.AdRepo
import co.vijay.class9thscience.repo.PdfRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    private val pdfRepository: PdfRepo,
    private val adRepository: AdRepo
) : ViewModel() {

    private var pdfPages: List<Bitmap> = emptyList()

    fun preparePdfForSaving(pages: List<Bitmap>) {
        pdfPages = pages
    }

    fun savePdf(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            pdfRepository.savePdf(pdfPages, uri)
        }
    }

    fun loadAd(activity: Activity) {
        adRepository.loadAd(activity)
    }

    fun showAd(activity: Activity) {
        adRepository.showAd(activity)
    }
}