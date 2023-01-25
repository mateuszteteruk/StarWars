package pl.mateuszteteruk.starwars.movie

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    val chips = listOf(
        "aaa",
        "bbb",
        "ccc",
        "ddd",
        "eee",
        "fff",
        "ggg"
    )
    val selectedChips = remember {
        mutableStateOf(emptyList<String>())
    }
    Movies(
        movies = movies,
        onMovieClick = {
            Log.d("TAG", "Movie click:$it")
            navigator.navigate(MovieDetailsDestination(movie = it.name))
        },
//        chips = if (selectedChips.value.isEmpty()) chips else selectedChips.value,
        chips = chips,
        selectedChips = selectedChips.value,
        onSelected = {
            if (selectedChips.value.contains(it)) {
                selectedChips.value = emptyList()
            } else {
                selectedChips.value = listOf(it)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Movies(
    movies: List<Movie>,
    chips: List<String>,
    selectedChips: List<String>,
    onMovieClick: (Movie) -> Unit,
    onSelected: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CollapsingTopBar(
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                item {
                    ChipsGroup(
                        items = chips,
                        selectedItems = selectedChips,
                        onSelected = onSelected,
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CollapsingTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
) {
    LargeTopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Movies",
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.LightGray.copy(alpha = 0.5f),
        ),
        actions = {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.Info,
                tint = Color.DarkGray,
                contentDescription = "Info",
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = Modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipsGroup(
    items: List<String>,
    selectedItems: List<String>,
    onSelected: (String) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(items = items) {
                FilterChip(
                    modifier = Modifier.padding(8.dp),
                    selected = selectedItems.contains(it),
                    onClick = {
                        onSelected(it)
                    },
                    label = { Text(text = it) }
                )
            }
        },
    )
}
