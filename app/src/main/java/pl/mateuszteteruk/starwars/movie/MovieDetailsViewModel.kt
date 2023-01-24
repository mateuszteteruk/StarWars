package pl.mateuszteteruk.starwars.movie

import androidx.lifecycle.ViewModel
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val player: Player,
) : ViewModel() {

    init {
        player.prepare()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
