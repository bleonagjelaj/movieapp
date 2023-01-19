package com.frakton.moviesapp.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BaseUseCase<InputType : BaseParameters, OutputType> {

    suspend operator fun invoke(input: InputType): OutputType
}

suspend inline fun <T> io(
    crossinline suspendFunction: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.IO) {
    suspendFunction()
}