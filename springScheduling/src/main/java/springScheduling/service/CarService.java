package springScheduling.service;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import springScheduling.domain.Car;
import springScheduling.repository.CarRepository;

import java.util.List;

/**
 * Created by Alexander
 * 07.09.2016
 */
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateCarAge() {
        for (Car car : findAll()) {
            car.setAge(Years.yearsBetween(car.getManufactureDate(), DateTime.now()).getYears());
            System.out.println(save(car));
        }
    }


}
