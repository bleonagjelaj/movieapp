package com.bleonahasanaj.moviesapp.util

interface BaseInputOutputInteractor<InputType, OutputType> {
    suspend operator fun invoke(input: InputType): OutputType
}