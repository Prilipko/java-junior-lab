/*
 * Copyright (c) 2017 Tideworks Technology, Inc.
 */

package times.zoned;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Change to summer time
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.8
 */
public class AbsentHour {
    public static void main(String[] args) {
//        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2017-08-11T13:21:40.632+01:00[Europe/Helsinki]");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.parse("2018-03-25T03:30:00.000+02:00[Europe/Helsinki]");
        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse("2018-03-25T03:30:00.000+03:00");
        ZonedDateTime zonedDateTime3 = ZonedDateTime.parse("2018-10-28T03:30:00.000+02:00[Europe/Helsinki]");
        ZonedDateTime zonedDateTime4 = ZonedDateTime.parse("2018-10-28T03:30:00.000+03:00[Europe/Helsinki]");
        Instant instant = zonedDateTime3.toInstant();
        Instant beforeInstant = instant.minus(1, ChronoUnit.HOURS);
        Instant afterInstant = instant.plus(1, ChronoUnit.HOURS);
        ZoneId zoneId = ZoneId.of("Europe/Helsinki");
        ZonedDateTime zonedDateTime5 = ZonedDateTime.ofInstant(beforeInstant, zoneId);
        ZonedDateTime zonedDateTime6 = ZonedDateTime.ofInstant(afterInstant, zoneId);

        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.toInstant(ZoneOffset.UTC);
        zonedDateTime1.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneOffset.UTC);
    }
}
