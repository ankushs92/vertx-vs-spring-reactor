package io.github.ankushs92.benchmark.vertxsample.handler

import io.github.ankushs92.benchmark.vertxsample.FakePojo
import io.github.ankushs92.benchmark.vertxsample.Json
import io.vertx.core.CompositeFuture
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import io.vertx.redis.RedisClient
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component

class ApiHandler implements Handler<RoutingContext> {

    private static final FAKE_HASH_KEY = "fake_hash_key"
    private static final FAKE_HASH_FIELDS_VALUE = "some_random_value"
    private static final FAKE_HASH_FIELD_1 = "fake_hash_field_1"
    private static final FAKE_HASH_FIELD_2 = "fake_hash_field_2"
    private static final FAKE_HASH_FIELD_3 = "fake_hash_field_3"
    private static final FAKE_HASH_FIELD_4 = "fake_hash_field_4"
    private static final FAKE_HASH_FIELD_5 = "fake_hash_field_5"
    private static final FAKE_HASH_FIELD_6 = "fake_hash_field_6"
    private static final FAKE_HASH_FIELD_7 = "fake_hash_field_7"
    private static final FAKE_HASH_FIELD_8= "fake_hash_field_8"
    private static final FAKE_HASH_FIELD_9 = "fake_hash_field_9"

    private final RedisClient redis

    ApiHandler(RedisClient redis) {
        this.redis = redis
    }

    @PostConstruct
    void putFakeHashesInRedis() {
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_1, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_2, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_3, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_4, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_5, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_6, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_7, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_8, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
        redis.hset(FAKE_HASH_KEY, FAKE_HASH_FIELD_9, FAKE_HASH_FIELDS_VALUE, Future.succeededFuture())
    }

    //Send off a series of commands to Redis via redis pipeline
    @Override
    void handle(RoutingContext rc) {
        def resp = rc.response()

        def future1 = getFakeValue(FAKE_HASH_FIELD_1)
        def future2 = getFakeValue(FAKE_HASH_FIELD_2)
        def future3 = getFakeValue(FAKE_HASH_FIELD_3)
        def future4 = getFakeValue(FAKE_HASH_FIELD_4)
        def future5 = getFakeValue(FAKE_HASH_FIELD_5)
        def future6 = getFakeValue(FAKE_HASH_FIELD_6)
        def future7 = getFakeValue(FAKE_HASH_FIELD_7)
        def future8 = getFakeValue(FAKE_HASH_FIELD_8)
        def future9 = getFakeValue(FAKE_HASH_FIELD_9)

        def futures = [future1, future2 ,future3 ,future4 ,future5 ,future6, future7, future8, future9 ]

        CompositeFuture.all(futures)
                       .setHandler { ar ->
                            if(ar.succeeded()) {
                                def results = ar.result().list()
                                def val1 = results[0]
                                def val2 = results[1]
                                def val3 = results[2]
                                def val4 = results[3]
                                def val5 = results[4]
                                def val6 = results[5]
                                def val7 = results[6]
                                def val8 = results[7]
                                def val9 = results[8]

                                def json = Json.encode(
                                        new FakePojo(
                                                value1 : val1,
                                                value2 : val2,
                                                value3 : val3,
                                                value4 : val4,
                                                value5 : val5,
                                                value6 : val6,
                                                value7 : val7,
                                                value8 : val8,
                                                value9 : val9,
                                        )
                                )
                                resp.putHeader("Content-Type", "application/json")
                                    .putHeader("Connection", "Keep-Alive")
                                    .end(json)
                            }
                            else {
                                rc.fail(ar.cause())
                            }
                       }

    }

    private Future<String> getFakeValue(String hashField) {
        def future = Future.future()
        redis.hget(FAKE_HASH_KEY, hashField, { ar ->
            if(ar.succeeded()) {
                future.complete(ar.result())
            }
            else {
                future.fail(ar.cause())
            }
        })
        future
    }

}
