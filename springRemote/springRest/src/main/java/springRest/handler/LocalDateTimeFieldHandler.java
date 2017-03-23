package springRest.handler;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * Created by Alexander Prilipko on 23.03.2017.
 */
public class LocalDateTimeFieldHandler extends GeneralizedFieldHandler {
    private DateTimeFormatter formatter;

    @Override
    public void setConfiguration(Properties config) throws ValidityException {
        formatter = DateTimeFormatter.ofPattern(config.getProperty("format"));
        super.setConfiguration(config);
    }

    @Override
    public Object convertUponGet(Object value) {
        return ((LocalDateTime) value).format(formatter);
    }

    @Override
    public Object convertUponSet(Object value) {
        return LocalDateTime.from(LocalDate.parse((String) value, formatter).atStartOfDay());
    }

    @Override
    public Class<LocalDateTime> getFieldType() {
        return LocalDateTime.class;
    }
}
