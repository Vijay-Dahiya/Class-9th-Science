package co.vijay.class9thscience.ui
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.vijay.class9thscience.ui.settings.AboutUsScreen
import co.vijay.class9thscience.ui.settings.AdDisclosureScreen
import co.vijay.class9thscience.ui.settings.ContactUsScreen
import co.vijay.class9thscience.ui.settings.PrivacyPolicyScreen
import co.vijay.class9thscience.ui.settings.SettingsScreen
import co.vijay.class9thscience.ui.settings.TermsOfServiceScreen
import co.vijay.class9thscience.utils.Routes
import co.vijay.class9thscience.utils.Screen

@Composable
fun App(modifier: Modifier = Modifier, onDownloadClick: (List<Bitmap>) -> Unit,onAdRequest: () -> Unit) {
    Box(modifier = modifier.padding(18.dp, 0.dp)) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.HOME) {
            composable(Routes.HOME) {
                UnitList(
                    itemClicked = { unitNumber ->
                        navController.navigate(Screen.PdfScreen.createRoute(unitNumber))
                        onAdRequest()
                    }, {
                        navController.navigate(Screen.Settings.route)
                    }
                )
            }
            composable(Screen.PdfScreen.route, arguments = listOf(
                navArgument("unitNumber") { type = NavType.IntType }
            )) {
                val unitNumber = it.arguments!!.getInt("unitNumber")
                SingleNoteScreen(
                    index = unitNumber,
                    onBackClick = { navController.popBackStack() },
                    onDownloadClick = onDownloadClick
                )
            }
            composable(Routes.SETTINGS) { SettingsScreen(navController) }
            composable(Routes.PRIVACY_POLICY) { PrivacyPolicyScreen(navController) }
            composable(Routes.ABOUT_US) { AboutUsScreen(navController) }
            composable(Routes.TERMS_OF_SERVICES) { TermsOfServiceScreen(navController) }
            composable(Routes.CONTACT_US) { ContactUsScreen(navController) }
            composable(Routes.AD_DISCLOSURE) { AdDisclosureScreen(navController) }
        }
    }
}