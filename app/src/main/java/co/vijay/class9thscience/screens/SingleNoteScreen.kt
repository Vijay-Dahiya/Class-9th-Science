package co.vijay.class9thscience.screens

import ProperMeasuredScrollView
import ZoomableScrollView
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.GestureDetectorCompat
import co.vijay.class9thscience.R
import java.io.File
import java.io.FileOutputStream
import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import co.vijay.class9thscience.utils.loadPdfFromRaw

@Composable
fun PdfViewerFromRawWithZoomAndScroll() {
    val context = LocalContext.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            // Create a LinearLayout to hold PDF pages
            val pdfLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
            }

            // Create a ProperMeasuredScrollView to wrap around the LinearLayout
            val scrollView = ProperMeasuredScrollView(context).apply {
                addView(pdfLayout)
            }

            // Load and display the PDF
            loadPdfFromRaw(context, pdfLayout, R.raw.unit3, scrollView) // Replace with your PDF resource

            scrollView
        }
    )
}

@Composable
fun SingleNoteScreen() {
    PdfViewerFromRawWithZoomAndScroll()
}
