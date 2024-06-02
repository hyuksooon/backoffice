package com.soon.backoffice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.soon.backoffice.domain"])
class BackofficeApplication

fun main(args: Array<String>) {
    runApplication<BackofficeApplication>(*args)
}
