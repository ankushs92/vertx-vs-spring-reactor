package io.github.ankushs92.benchmark.vertxsample.config

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VertxConfig {

    @Bean
    Vertx vertx() {
        Vertx.vertx()
    }

    @Bean
    HttpServer httpServer() {
         vertx()
                .createHttpServer()
                .requestHandler(router().&accept)
                .listen(8080)
    }

    @Bean
    Router router() {
        Router.router(vertx())
    }

}
