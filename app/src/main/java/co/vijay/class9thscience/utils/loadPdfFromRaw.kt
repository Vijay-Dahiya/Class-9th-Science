package co.vijay.class9thscience.utils

import android.content.Context
import android.graphics.Bitmap

import android.graphics.Matrix
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import java.io.File
import java.io.FileOutputStream

fun loadPdfFromRaw(context: Context, pdfLayout: LinearLayout, rawResId: Int, scrollView: ScrollView) {
    val inputStream = context.resources.openRawResource(rawResId)
    val file = File(context.cacheDir, "temp_pdf.pdf")
    val outputStream = FileOutputStream(file)
    inputStream.copyTo(outputStream)
    inputStream.close()
    outputStream.close()

    val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    val pdfRenderer = PdfRenderer(fileDescriptor)

    for (i in 0 until pdfRenderer.pageCount) {
        val page = pdfRenderer.openPage(i)
        val width = page.width * 2
        val height = page.height * 2
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

        val imageView = ImageView(context)
        imageView.setImageBitmap(bitmap)
        imageView.adjustViewBounds = true

        pdfLayout.addView(imageView)
        page.close()
    }

    // Force layout re-calculation after adding all pages
    scrollView.post {
        scrollView.requestLayout()
        scrollView.invalidate()
    }

    pdfRenderer.close()
}