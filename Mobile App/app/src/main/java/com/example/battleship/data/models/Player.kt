package com.example.battleship.data.models

data class Player(
    var name: String?,
    var email: String?,
    var auth_key: String?,
    var n_win_games: Integer?,
    var n_lose_games: Integer?,
    var n_played_games: Integer?,
    var n_bonifications: Integer?,
    var n_effectiveness: Integer?,
    var turns_mean_of_games: Integer?,
    var mean_of_misses_by_game: Integer?
)