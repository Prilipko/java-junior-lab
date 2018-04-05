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
public class JustWrite {

    @JsonIgnore
    private String field;

    @JsonProperty
    private String other;

    public JustWrite() {
    }

    public JustWrite(final String field) {
        this.field = field;
    }

    @JsonIgnore
    public String getField() {
        return field;
    }

    @JsonProperty
    public void setField(final String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "JustWrite{" +
                "field='" + field + '\'' +
                '}';
    }
}
