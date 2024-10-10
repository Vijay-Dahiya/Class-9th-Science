package co.vijay.class9thscience.repo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.use
import java.io.FileOutputStream
import javax.inject.Inject

class PdfRepo @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun savePdf(pdfPages: List<Bitmap>, uri: Uri) = withContext(Dispatchers.IO) {
        val pdfDocument = PdfDocument()
        try {
            pdfPages.forEachIndexed { index, bitmap ->
                val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, index + 1).create()
                val page = pdfDocument.startPage(pageInfo)
                page.canvas.drawBitmap(bitmap, 0f, 0f, null)
                pdfDocument.finishPage(page)
            }
            context.contentResolver.openFileDescriptor(uri, "w")?.use { parcelFileDescriptor ->
                FileOutputStream(parcelFileDescriptor.fileDescriptor).use { outputStream ->
                    pdfDocument.writeTo(outputStream)
                }
            }
        } finally {
            pdfDocument.close()
        }
    }
}
