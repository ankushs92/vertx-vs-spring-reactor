package io.github.ankushs92.benchmark.vertxsample

import com.fasterxml.jackson.annotation.JsonProperty

class FakePojo {

    @JsonProperty
    final String value1

    @JsonProperty
    final String value2

    @JsonProperty
    final String value3

    @JsonProperty
    final String value4

    @JsonProperty
    final String value5

    @JsonProperty
    final String value6

    @JsonProperty
    final String value7

    @JsonProperty
    final String value8

    @JsonProperty
    final String value9

    FakePojo(Map fields) {
        this.value1 = fields['value1'] as String
        this.value2 = fields['value2'] as String
        this.value3 = fields['value3'] as String
        this.value4 = fields['value4'] as String
        this.value5 = fields['value5'] as String
        this.value6 = fields['value6'] as String
        this.value7 = fields['value7'] as String
        this.value8 = fields['value8'] as String
        this.value9 = fields['value9'] as String
    }


}
