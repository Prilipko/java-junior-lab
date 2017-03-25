package amqp;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Alexander Prilipko on 25.03.2017.
 */
public class AmqpRpcSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:amqp-rpc-app-context.xml");
        context.refresh();

        WeatherService weatherService = context.getBean(WeatherService.class);
        System.out.println("Forecast for FL " + weatherService.getForecast("FL"));
        System.out.println("Forecast for MA " + weatherService.getForecast("MA"));
        System.out.println("Forecast for CA " + weatherService.getForecast("CA"));
    }
}
