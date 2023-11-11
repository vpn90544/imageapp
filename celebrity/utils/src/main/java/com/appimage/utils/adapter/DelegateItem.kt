package com.appimage.utils.adapter

interface DelegateItem {

    fun id(): Any = hashCode()

    fun content(): Any = this

    fun payload(other: Any): List<Payloadable> = listOf()

    interface Payloadable
}