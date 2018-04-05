/*
 * Copyright (c) 2018 Tideworks Technology, Inc.
 */

package jackson.IgnoreAndProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.11
 */
public class JustReadNoFieldAnnotation {

//    @JsonIgnore
    private String field;

    public JustReadNoFieldAnnotation() {
    }

    public JustReadNoFieldAnnotation(final String field) {
        this.field = field;
    }

    @JsonProperty
    public String getField() {
        return field;
    }

    @JsonIgnore
    public void setField(final String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "JustReadNoFieldAnnotation{" +
                "field='" + field + '\'' +
                '}';
    }
}
