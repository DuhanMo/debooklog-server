package org.debooklog.debooklogserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@ConfigurationPropertiesScan
@SpringBootApplication
class DebooklogServerApplication

fun main(args: Array<String>) {
    runApplication<DebooklogServerApplication>(*args)
}
