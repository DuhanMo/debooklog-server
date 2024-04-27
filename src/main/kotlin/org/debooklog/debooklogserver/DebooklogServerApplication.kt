package org.debooklog.debooklogserver

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@OpenAPIDefinition(
    info =
        Info(
            title = "Debooklog Server",
            version = "1.0",
            description = "개발자를 위한 독서기록 커뮤니티",
        ),
)
@EnableFeignClients
@ConfigurationPropertiesScan
@SpringBootApplication
class DebooklogServerApplication

fun main(args: Array<String>) {
    runApplication<DebooklogServerApplication>(*args)
}
