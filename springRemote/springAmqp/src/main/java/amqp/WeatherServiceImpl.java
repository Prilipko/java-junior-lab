package amqp;

/**
 * Created by Alexander Prilipko on 25.03.2017.
 */
public class WeatherServiceImpl implements WeatherService {
    @Override
    public String getForecast(String stateCode) {
        switch (stateCode) {
            case "FL":
                return "Hot";
            case "MA":
                return "Cold";
            default:
                return "Not available at this time";
        }
    }
}
