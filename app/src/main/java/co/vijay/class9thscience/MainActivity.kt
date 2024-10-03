package co.vijay.class9thscience

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.vijay.class9thscience.screens.SingleNoteScreen
import co.vijay.class9thscience.screens.UnitList
import co.vijay.class9thscience.ui.theme.Class9thScienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Class9thScienceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFB8D2E6),  // Light blue
                                Color(0xFFD5BED8)   // Light purple
                            )
                        )).padding(innerPadding).fillMaxSize())
                }
            }
        }
    }
}

@Preview
@Composable
fun App( modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(18.dp,0.dp)){
        val c = LocalContext.current
//        SingleNoteScreen(3)
        UnitList{
            Toast.makeText(c, it.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}




