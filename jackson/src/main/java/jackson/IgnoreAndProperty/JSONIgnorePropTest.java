/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package jackson.IgnoreAndProperty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
public class JSONIgnorePropTest {

    private static final String VALUE = "some value";

    public static void main(String[] args) {

        testEntity(new JustRead(VALUE), JustRead.class);
        System.out.println();
        testEntity(new JustReadNoFieldAnnotation(VALUE), JustReadNoFieldAnnotation.class);
        System.out.println();
        testEntity(new JustReadNoSetterAnnotation(VALUE), JustReadNoSetterAnnotation.class);
        System.out.println();
        System.out.println();
        testEntity(new JustWrite(VALUE), JustWrite.class);
        System.out.println();
        testEntity(new JustWriteNoFieldAnnotation(VALUE), JustWriteNoFieldAnnotation.class);
        System.out.println();
        testEntity(new JustWriteNoGetterAnnotation(VALUE), JustWriteNoGetterAnnotation.class);
    }

    private static <T> void testEntity(T inputObj, Class<T> clazz) {

        System.out.println("inputObj: " + inputObj);

        ObjectMapper mapper = new ObjectMapper();
        final String outputStr;
        try {
            outputStr = mapper.writeValueAsString(inputObj);
            System.out.println("outputStr: " + outputStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        final String inputStr = "{\"field\":\"" + VALUE + "\"}";
        System.out.println("inputStr: " + inputStr);

        final T outputObj;
        try {
            outputObj = mapper.readValue(inputStr, clazz);
            System.out.println("outputObj: " + outputObj.toString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}