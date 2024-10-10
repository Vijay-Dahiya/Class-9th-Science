package co.vijay.class9thscience.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import co.vijay.class9thscience.ui.theme.Class9thScienceTheme
import co.vijay.class9thscience.viewmodel.PdfViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val pdfViewModel: PdfViewModel by viewModels()

    private val createFile = registerForActivityResult(CreateDocument("application/pdf")) { uri ->
        uri?.let {
            pdfViewModel.savePdf(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pdfViewModel.loadAd(this@MainActivity)
        setContent {
            Class9thScienceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFB8D2E6),
                                        Color(0xFFD5BED8)
                                    )
                                )
                            )
                            .padding(innerPadding)
                            .fillMaxSize(),
                        onDownloadClick = { pdfPages ->
                            pdfViewModel.preparePdfForSaving(pdfPages)
                            createFile.launch("Science.pdf")
                        },
                        onAdRequest = {
                            pdfViewModel.showAd(this)
                        }
                    )
                }
            }
        }
    }
}




