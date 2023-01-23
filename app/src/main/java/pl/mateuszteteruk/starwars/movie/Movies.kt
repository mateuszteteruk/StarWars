package pl.mateuszteteruk.starwars.movie

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pl.mateuszteteruk.starwars.movie.destinations.MovieDetailsDestination

@Destination(start = true)
@Composable
fun Movies(
    navigator: DestinationsNavigator,
) {
    val movies = listOf(
        Movie(name = "A New Hope"),
        Movie(name = "The Empire Strikes Back"),
        Movie(name = "Return of the Jedi"),
        Movie(name = "The Phantom Menace"),
        Movie(name = "Attack of the Clones"),
        Movie(name = "Revenge of the Sith"),
    )
    Movies(
        movies = movies,
        onMovieClick = {
            Log.d("TAG", "Movie click:$it")
            navigator.navigate(MovieDetailsDestination(movie = it.name))
        }
    )
}

@Composable
private fun Movies(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(
                items = movies,
                key = { it.name },
            ) { movie ->
                MovieItem(
                    movie = movie,
                    onMovieClick = onMovieClick,
                )
            }
        }
    )
}

@Composable
private fun MovieItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable {
                onMovieClick(movie)
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = movie.name,
            modifier = Modifier.padding(8.dp),
        )
    }
}
