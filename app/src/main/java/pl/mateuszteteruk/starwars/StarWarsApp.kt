package pl.mateuszteteruk.starwars

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import pl.mateuszteteruk.starwars.movie.NavGraphs
import pl.mateuszteteruk.starwars.ui.theme.StarWarsTheme

@Composable
fun StarWarsApp() {
    StarWarsTheme {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            startRoute = NavGraphs.root.startRoute,
        )
    }
}

