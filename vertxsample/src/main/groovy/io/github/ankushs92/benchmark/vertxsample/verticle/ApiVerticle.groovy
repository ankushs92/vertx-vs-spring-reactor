package io.github.ankushs92.benchmark.vertxsample.verticle

import io.github.ankushs92.benchmark.vertxsample.handler.ApiHandler
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component

class ApiVerticle extends AbstractVerticle {

    private final Router router
    private final ApiHandler apiHandler

    ApiVerticle(
            Router router,
            ApiHandler apiHandler
    )
    {
        this.router = router
        this.apiHandler = apiHandler
    }

    @Override
    void start() {

        router.get("/fake/value")
              .handler(apiHandler)

    }

}
