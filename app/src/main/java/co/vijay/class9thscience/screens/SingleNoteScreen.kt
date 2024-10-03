package co.vijay.class9thscience.screens

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import co.vijay.class9thscience.R
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.io.File
import java.io.FileOutputStream

@Composable
fun OpenPdf(r : Int) {
    val context = LocalContext.current
    var pdfPages by remember { mutableStateOf(listOf<Bitmap>()) }

    // Step 1: Load PDF pages into a Bitmap list using PdfRenderer
    LaunchedEffect(Unit) {
        val inputStream = context.resources.openRawResource(r) // Replace with your PDF resource
        val file = File(context.cacheDir, "temp_pdf.pdf")
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val pdfRenderer = PdfRenderer(fileDescriptor)

        val pages = mutableListOf<Bitmap>()
        for (i in 0 until pdfRenderer.pageCount) {
            val page = pdfRenderer.openPage(i)
            val width = page.width * 2
            val height = page.height * 2
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            pages.add(bitmap)
            page.close()
        }
        pdfRenderer.close()
        fileDescriptor.close()
        pdfPages = pages
    }

    // State for managing zoom and pan
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    // Box for handling pinch-to-zoom, double-tap zoom, and panning
    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                size = coordinates.size  // Capture the size of the image for boundary calculations
            }
            .pointerInput(Unit) {
                detectTransformGestures(
                    onGesture = { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 3f)  // Constrain zoom between 1x and 3x

                        // Limit panning to prevent white space from appearing
                        val maxX = (size.width * (scale - 1)) / 2
                        val maxY = (size.height * (scale - 1)) / 2

                        offsetX = (offsetX + pan.x).coerceIn(-maxX, maxX)
                        offsetY = (offsetY + pan.y).coerceIn(-maxY, maxY)
                    }
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        if (scale > 1f) {
                            scale = 1f  // Double-tap zooms out if already zoomed
                            offsetX = 0f
                            offsetY = 0f
                        } else {
                            scale = 2f  // Double-tap zooms in to 2x
                        }
                    }
                )
            }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offsetX,
                translationY = offsetY
            )
    ) {
        // Display the PDF pages in a scrollable LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(pdfPages) { pageBitmap ->
                AsyncImage(
                    model = pageBitmap,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)  // Add padding between pages to avoid overlap
                        .border(2.dp, shape =  RectangleShape, color = Color.Yellow)
                )
            }
        }
    }
}
@Composable
fun SingleNoteScreen(index : Int) {
    when(index) {
        0 -> {

        }

        1 -> {

        }

        2 -> {

        }

        3 -> {
            OpenPdf(R.raw.unit3)
        }

        4 -> {

        }
    }

}
