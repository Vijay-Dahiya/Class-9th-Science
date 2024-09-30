import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ScrollView
import kotlin.math.max
import kotlin.math.min

class ZoomableScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ScrollView(context, attrs) {

    private var scaleFactor = 1.0f
    private val scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    private val gestureDetector = GestureDetector(context, GestureListener())
    private var matrix = Matrix()
    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private var posX = 0f
    private var posY = 0f

    init {
        // Enable scaling and translation
        setWillNotDraw(false)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                // Handle dragging/panning
                val dx = event.x - lastTouchX
                val dy = event.y - lastTouchY
                posX += dx
                posY += dy

                // Restrict panning within boundaries
                posX = max(min(posX, 0f), -(width * scaleFactor - width))
                posY = max(min(posY, 0f), -(height * scaleFactor - height))

                invalidate()
            }
        }
        lastTouchX = event.x
        lastTouchY = event.y
        return true
    }

    override fun dispatchDraw(canvas: android.graphics.Canvas) {
        canvas.save()
        canvas.translate(posX, posY)
        canvas.scale(scaleFactor, scaleFactor)
        super.dispatchDraw(canvas)
        canvas.restore()
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = max(1.0f, min(scaleFactor, 3.0f)) // Limit zoom levels
            invalidate()
            return true
        }
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            // Toggle zoom on double-tap
            if (scaleFactor > 1f) {
                scaleFactor = 1f
                posX = 0f
                posY = 0f
            } else {
                scaleFactor = 2f
            }
            invalidate()
            return true
        }
    }
}
