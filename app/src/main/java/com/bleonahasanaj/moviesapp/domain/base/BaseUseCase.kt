package com.bleonahasanaj.moviesapp.domain.base

interface BaseUseCase<InputType : BaseParameters, OutputType> {
    suspend operator fun invoke(input: InputType): OutputType
}