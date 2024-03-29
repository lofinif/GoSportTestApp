package com.lofinif.gosporttestapp.ui

interface BaseMapper<A, B> {
    fun map(item: A): B
}