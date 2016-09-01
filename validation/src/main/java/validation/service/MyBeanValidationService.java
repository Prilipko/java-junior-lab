package validation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.domain.Customer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class MyBeanValidationService {
    @Autowired
    Validator validator;

    public Set<ConstraintViolation<Customer>> validateCustomer (Customer customer){
        return validator.validate(customer);
    }
}
