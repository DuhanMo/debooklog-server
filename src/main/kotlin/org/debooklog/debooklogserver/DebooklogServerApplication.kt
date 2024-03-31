package org.debooklog.debooklogserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DebooklogServerApplication

fun main(args: Array<String>) {
    runApplication<DebooklogServerApplication>(*args)
}
