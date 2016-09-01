package validation;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean{
    private String datePattern = "yyyy-MM-dd";
    private DateTimeFormatter formatter;
    private Set<Formatter<?>> formatters = new HashSet<>();

    public String getDatePattern() {
        return datePattern;
    }

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    private void init(){
        formatter = DateTimeFormat.forPattern(datePattern);
        formatters.add(getDateTimeFormatter());
        setFormatters(formatters);
    }

    private Formatter<DateTime> getDateTimeFormatter(){
        return new Formatter<DateTime>() {
            @Override
            public DateTime parse(String s, Locale locale) throws ParseException {
                System.out.println("parse : " + s);
                return formatter.parseDateTime(s);
            }

            @Override
            public String print(DateTime dateTime, Locale locale) {
                System.out.println("print : "+dateTime);
                return formatter.print(dateTime);
            }
        };
    }
}
