package springScheduling.repository;

import org.springframework.data.repository.CrudRepository;
import springScheduling.domain.Car;

/**
 * Created by Alexander
 * 07.09.2016
 */
public interface CarRepository extends CrudRepository<Car, Long> {
}
