package pl.mateuszteteruk.starwars

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pl.mateuszteteruk.starwars.movie.Movie
import pl.mateuszteteruk.starwars.movie.Movies
import pl.mateuszteteruk.starwars.ui.theme.StarWarsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsTheme {
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
                    }
                )
            }
        }
    }
}
