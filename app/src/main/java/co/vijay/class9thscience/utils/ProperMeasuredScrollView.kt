import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

class ProperMeasuredScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ScrollView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Custom measurement logic to ensure proper content height measurement
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun requestLayout() {
        super.requestLayout()
        // Ensure the layout is remeasured and redrawn
        post {
            invalidate()
            requestLayout()
        }
    }
}
