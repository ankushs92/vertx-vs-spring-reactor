package io.github.ankushs92.benchmark.vertxsample;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Helper class to serialize/deserialize POJOs/Maps to JSON and vice versa
 * Created by Ankush on 17/07/17.
 */
public class Json {

    //Reasons for static initialization, check out this link:
    //https://stackoverflow.com/questions/18611565/how-do-i-correctly-reuse-jackson-objectmapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    /**
     * Serialize a POJO/Map to JSON String
     *
     * @param object The Object to convert to a JSON string
     * @return The JSON representation of @param object
     * @throws Exception
     */
    public static String encode(final Object object) throws Exception {
        return objectMapper
                .writeValueAsString(object);
    }


}
