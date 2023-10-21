package com.davintiproject.backend.common.domain

interface Command<TParams, TReturn> {
    fun execute(params: TParams): TReturn
}


interface Query<TParams, TReturn> {
    fun execute(params: TParams): TReturn
}

