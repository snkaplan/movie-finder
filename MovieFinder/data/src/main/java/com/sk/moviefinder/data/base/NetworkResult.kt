package com.sk.moviefinder.data.base

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

