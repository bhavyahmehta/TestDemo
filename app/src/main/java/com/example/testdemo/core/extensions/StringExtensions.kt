package com.example.testdemo.core.extensions

fun String?.isValid(): Boolean {
    return this != null && this!="null" && this.trim().isNotBlank()
}