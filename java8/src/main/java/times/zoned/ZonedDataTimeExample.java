/*
 * Copyright (c) 2017 Tideworks Technology, Inc.
 */

package times.zoned;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Example for {@link ZonedDateTime}
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.8
 */
public class ZonedDataTimeExample {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime: " + zonedDateTime);
        System.out.println("zonedDateTime.getOffset(): " + zonedDateTime.getOffset());
        System.out.println("zonedDateTime.getZone(): " + zonedDateTime.getZone());
        System.out.println("zonedDateTime.getChronology(): " + zonedDateTime.getChronology());

        System.out.println("zonedDateTime.toLocalDateTime(): " + zonedDateTime.toLocalDateTime());
        System.out.println("zonedDateTime.toOffsetDateTime(): " + zonedDateTime.toOffsetDateTime());
        System.out.println("zonedDateTime.toInstant(): " + zonedDateTime.toInstant());

        ZonedDateTime otherZoned = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println("otherZoned: " + otherZoned);
        System.out.println("otherZoned.getOffset(): " + otherZoned.getOffset());
        System.out.println("otherZoned.getZone(): " + otherZoned.getZone());
        System.out.println("otherZoned.getChronology(): " + otherZoned.getChronology());

        System.out.println("otherZoned.toLocalDateTime(): " + otherZoned.toLocalDateTime());
        System.out.println("otherZoned.toOffsetDateTime(): " + otherZoned.toOffsetDateTime());
        System.out.println("otherZoned.toInstant(): " + otherZoned.toInstant());

        System.out.println("Duration: " + Duration.between(zonedDateTime, otherZoned).toMillis() + "ms");

        ZoneId newClientViewPoint = ZoneId.of("Europe/London");

        System.out.println("In London first: "+zonedDateTime.withZoneSameInstant(newClientViewPoint));
        System.out.println("In London second: "+otherZoned.withZoneSameInstant(newClientViewPoint));
    }
}
