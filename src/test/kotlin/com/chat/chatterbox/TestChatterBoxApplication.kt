package com.chat.chatterbox

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ChatterBoxApplication>().with(TestcontainersConfiguration::class).run(*args)
}
