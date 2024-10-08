package co.vijay.class9thscience.ui

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import co.vijay.class9thscience.R
import coil.compose.AsyncImage
import java.io.File
import java.io.FileOutputStream

@Composable
fun OpenPdf(rawId: Int, onBackClick: () -> Unit, onDownloadClick: (pdfPages: List<Bitmap>) -> Unit) {
    val context = LocalContext.current
    var pdfPages by remember { mutableStateOf(listOf<Bitmap>()) }
    var isLoading by remember { mutableStateOf(true) }
    val listState = rememberLazyListState()
    var showBackButton by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val inputStream = context.resources.openRawResource(rawId)
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
        isLoading = false
    }

    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }


    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                val threshold = size.height * 0.01f
                showBackButton = scrollOffset <= threshold
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                size = coordinates.size
            }
            .pointerInput(Unit) {
                detectTransformGestures(
                    onGesture = { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 3f)

                        val maxX = (size.width * (scale - 1)) / 2
                        val maxY = (size.height * (scale - 1)) / 2

                        offsetX = (offsetX + pan.x).coerceIn(-maxX, maxX)
                        offsetY = (offsetY + pan.y).coerceIn(-maxY, maxY)
                        showBackButton = false
                    }
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        if (scale > 1f) {
                            scale = 1f
                            offsetX = 0f
                            offsetY = 0f
                        } else {
                            scale = 2f
                        }
                        showBackButton = false
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(pdfPages) { pageBitmap ->
                AsyncImage(
                    model = pageBitmap,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(2.dp, shape = RectangleShape, color = Color.Yellow)
                )
            }
        }
    }
    if (showBackButton) {
        Box(modifier = Modifier.fillMaxSize()) {

            FloatingActionButton(
                onClick = onBackClick,
                contentColor = Color.White,
                modifier = Modifier
                    .background(color = Color.Transparent, shape = CircleShape)
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
            }

            FloatingActionButton(
                onClick = { onDownloadClick(pdfPages) },
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    modifier = Modifier.rotate(270f),
                    contentDescription = "Download"
                )
            }
        }
    }


}

@Composable
fun SingleNoteScreen(index: Int, onBackClick: () -> Unit, onDownloadClick: (pdfPages: List<Bitmap>) -> Unit) {
    when (index) {
        0 -> {

        }

        1 -> {
            OpenPdf(R.raw.unit1, onBackClick, onDownloadClick)
        }

        2 -> {
            OpenPdf(R.raw.unit2, onBackClick, onDownloadClick)
        }

        3 -> {
            OpenPdf(R.raw.unit3, onBackClick, onDownloadClick)
        }

        4 -> {
            OpenPdf(R.raw.unit4, onBackClick, onDownloadClick)
        }

        5 -> {
            OpenPdf(R.raw.unit5, onBackClick, onDownloadClick)
        }
    }

}
