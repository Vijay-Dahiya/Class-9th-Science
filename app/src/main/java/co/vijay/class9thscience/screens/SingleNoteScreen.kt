package co.vijay.class9thscience.screens


import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.vijay.class9thscience.R
import java.io.File
import java.io.FileOutputStream

@Composable
fun PdfViewer() {
    val context = LocalContext.current
    var bitmaps by remember { mutableStateOf<List<Bitmap?>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            // Step 1: Copy PDF from res/raw to cache directory
            val inputStream = context.resources.openRawResource(R.raw.unit3) // Replace with your PDF file name
            val file = File(context.cacheDir, "unit3.pdf")
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            // Step 2: Open the PDF file using PdfRenderer
            val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)

            // Step 3: Render each page to a Bitmap and collect the results
            val pages = mutableListOf<Bitmap?>()
            for (i in 0 until pdfRenderer.pageCount) {
                val page = pdfRenderer.openPage(i)
                val width = page.width * 2 // Increase resolution (optional)
                val height = page.height * 2
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                page.close()
                pages.add(bitmap)
            }

            // Close the PdfRenderer after use
            pdfRenderer.close()
            bitmaps = pages

        } catch (e: Exception) {
            // Log and display error messages
            errorMessage = "Error loading PDF: ${e.localizedMessage}"
            Log.e("PdfViewer", "Error loading PDF", e)
        }
    }

    // Step 4: Display the PDF pages or an error message
    if (errorMessage != null) {
        Text(text = errorMessage ?: "Unknown error")
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(bitmaps) { index, bitmap ->
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "PDF Page $index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SingleNoteScreen() {
    PdfViewer()
}

