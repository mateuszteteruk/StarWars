package pl.mateuszteteruk.starwars.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MovieDetails(
    movie: String,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    MovieContent(movieTitle = movie)
}

@Composable
private fun MovieContent(
    movieTitle: String,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            text = movieTitle
        )
    }
}
