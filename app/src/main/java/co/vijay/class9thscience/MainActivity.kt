package co.vijay.class9thscience

import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.vijay.class9thscience.screens.SingleNoteScreen
import co.vijay.class9thscience.screens.UnitList
import co.vijay.class9thscience.ui.theme.Class9thScienceTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {


    private val createFile = registerForActivityResult(CreateDocument()) { uri ->
        uri?.let {
            lifecycleScope.launch(Dispatchers.IO) {
                createFile(it)
            }
        }
    }

    private var pdfData : List<Bitmap>? = null

    private fun downloadPdf(pdfPages: List<Bitmap>) {
        pdfData = pdfPages
        createFile.launch("Unit 3 Science.pdf")
    }

    private fun createFile(uri: Uri) {
        pdfData?.let { bitmaps ->
            val pdfDocument = PdfDocument()
            try {
                for ((index, bitmap) in bitmaps.withIndex()) {
                    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, index + 1).create()
                    val page = pdfDocument.startPage(pageInfo)
                    page.canvas.drawBitmap(bitmap, 0f, 0f, null)
                    pdfDocument.finishPage(page)
                }
                writePdfToFile(pdfDocument, uri)
            } finally {
                pdfDocument.close() // Make sure to close the PdfDocument
            }
        }
    }

    private fun writePdfToFile(pdfDocument: PdfDocument, uri: Uri) {
        try {
            contentResolver.openFileDescriptor(uri, "w")?.use { parcelFileDescriptor ->
                FileOutputStream(parcelFileDescriptor.fileDescriptor).use { outputStream ->
                    pdfDocument.writeTo(outputStream)
                }
            }
            runOnUiThread {
                Toast.makeText(this, "PDF saved successfully.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            runOnUiThread {
                Toast.makeText(this, "Failed to save PDF: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Class9thScienceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFB8D2E6),  // Light blue
                                    Color(0xFFD5BED8)   // Light purple
                                )
                            )
                        )
                        .padding(innerPadding)
                        .fillMaxSize() , downloadPdf = ::downloadPdf
                    )
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier, downloadPdf: (pdfPages: List<Bitmap>) -> Unit) {
    Box(modifier = modifier.padding(18.dp,0.dp)){
        val c = LocalContext.current
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home"){
                UnitList {
                    navController.navigate("pdf_screen/${it}")
                }
            }

            composable("pdf_screen/{unitNumber}", arguments =  listOf(
                navArgument("unitNumber") {
                    type = NavType.IntType
                }
            )) {
                val a = it.arguments!!.getInt("unitNumber")
                SingleNoteScreen(index = a,
                    { navController.popBackStack() },
                    downloadPdf
                )
            }
        }
    }
}




