package io.github.ankushs92.benchmark.springreactorsample

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@org.springframework.web.bind.annotation.RestController
class RestController {

    private static final FAKE_HASH_KEY = "fake_hash_key"
    private static final FAKE_HASH_FIELD_1 = "fake_hash_field_1"
    private static final FAKE_HASH_FIELD_2 = "fake_hash_field_2"
    private static final FAKE_HASH_FIELD_3 = "fake_hash_field_3"
    private static final FAKE_HASH_FIELD_4 = "fake_hash_field_4"
    private static final FAKE_HASH_FIELD_5 = "fake_hash_field_5"
    private static final FAKE_HASH_FIELD_6 = "fake_hash_field_6"
    private static final FAKE_HASH_FIELD_7 = "fake_hash_field_7"
    private static final FAKE_HASH_FIELD_8= "fake_hash_field_8"
    private static final FAKE_HASH_FIELD_9 = "fake_hash_field_9"

    private final ReactiveRedisTemplate<String, String> lettuce

    RestController(ReactiveRedisTemplate<String, String> lettuce) {
        this.lettuce = lettuce
    }

    @GetMapping("/fake/value")
    Mono<FakePojo> getFakeValues() {
        def value1Mono = fakeValue(FAKE_HASH_FIELD_1)
        def value2Mono = fakeValue(FAKE_HASH_FIELD_2)
        def value3Mono = fakeValue(FAKE_HASH_FIELD_3)
        def value4Mono = fakeValue(FAKE_HASH_FIELD_4)
        def value5Mono = fakeValue(FAKE_HASH_FIELD_5)
        def value6Mono = fakeValue(FAKE_HASH_FIELD_6)
        def value7Mono = fakeValue(FAKE_HASH_FIELD_7)
        def value8Mono = fakeValue(FAKE_HASH_FIELD_8)
        def value9Mono = fakeValue(FAKE_HASH_FIELD_9)
        Mono.<FakePojo>zip([value1Mono, value2Mono, value3Mono, value4Mono, value5Mono, value6Mono, value7Mono, value8Mono, value9Mono], { values ->
            def pojo = new FakePojo(
                    value1 : values[0],
                    value2 : values[1],
                    value3 : values[2],
                    value4 : values[3],
                    value5 : values[4],
                    value6 : values[5],
                    value7 : values[6],
                    value8 : values[7],
                    value9 : values[8]
            )
            pojo
        })
    }

    private Mono<String> fakeValue(String hashField) {
        def hashOps = lettuce.opsForHash()
        hashOps.get(FAKE_HASH_KEY, hashField)
    }

}
