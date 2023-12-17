package com.demo.kiseong.yoo.demo

import com.demo.kiseong.yoo.demo.calculator.dto.CalculateBody

fun generateRequestUrl(port: Int, baseUrl: String, endPoint: String): String {
    return "http://localhost:$port/$baseUrl/$endPoint"
}

fun <T> appendParams(url: String, a: T, b: T): String {
    return "$url?a=$a&b=$b"
}

fun <T> generateBody(a: T, b: T): CalculateBody<T> {
    return CalculateBody(a, b)
}
