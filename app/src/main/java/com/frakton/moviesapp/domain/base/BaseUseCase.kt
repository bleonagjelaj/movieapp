package com.frakton.moviesapp.domain.base

interface BaseUseCase<InputType : BaseParameters, OutputType> {
    suspend operator fun invoke(input: InputType): OutputType
}