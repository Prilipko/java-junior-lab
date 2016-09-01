package validation;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter implements Converter<String, DateTime> {
    private String datePattern = "yyyy-MM-dd";
    private DateTimeFormatter formatter;

    @Override
    public DateTime convert(String s) {
        return formatter.parseDateTime(s);
    }

    public String getDatePattern() {
        return datePattern;
    }

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        formatter = DateTimeFormat.forPattern(datePattern);
    }
}
