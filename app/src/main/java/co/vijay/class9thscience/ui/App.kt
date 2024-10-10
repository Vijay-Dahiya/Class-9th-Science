package co.vijay.class9thscience.ui
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun App(modifier: Modifier = Modifier, onDownloadClick: (List<Bitmap>) -> Unit,onAdRequest: () -> Unit) {
    Box(modifier = modifier.padding(18.dp, 0.dp)) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                UnitList { unitNumber ->
                    navController.navigate("pdf_screen/$unitNumber")
                    onAdRequest()
                }
            }
            composable("pdf_screen/{unitNumber}", arguments = listOf(
                navArgument("unitNumber") {
                    type = NavType.IntType
                }
            )) {
                val a = it.arguments!!.getInt("unitNumber")
                SingleNoteScreen(
                    index = a,
                    { navController.popBackStack() },
                    onDownloadClick
                )
            }
        }
    }
}