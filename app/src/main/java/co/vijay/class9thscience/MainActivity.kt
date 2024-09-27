package co.vijay.class9thscience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.vijay.class9thscience.screens.SingleNoteScreen
import co.vijay.class9thscience.ui.theme.Class9thScienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Class9thScienceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App( modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(18.dp,0.dp)){
        val c = LocalContext.current
        SingleNoteScreen()
//        UnitList{
//
//            Toast.makeText(c, it.toString(), Toast.LENGTH_SHORT).show()
//        }

    }


}




