package co.vijay.class9thscience.utils

sealed class Screen(val route: String) {
    object Home : Screen(Routes.HOME)
    object PdfScreen : Screen("${Routes.PDF_SCREEN}/{unitNumber}") {
        fun createRoute(unitNumber: Int) = "${Routes.PDF_SCREEN}/$unitNumber"
    }
    object Settings : Screen(Routes.SETTINGS)
    object PrivacyPolicy : Screen(Routes.PRIVACY_POLICY)
    object AboutUs : Screen(Routes.ABOUT_US)
    object TermsOfService : Screen(Routes.TERMS_OF_SERVICES)
    object ContactUs : Screen(Routes.CONTACT_US)
    object AdDisclosure : Screen(Routes.AD_DISCLOSURE)
}
