package validation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import validation.domain.Customer;
import validation.domain.CustomerType;
import validation.service.MyBeanValidationService;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

public class JSR349Validation {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jsr349-app-context.xml");

        MyBeanValidationService beanValidationService = context.getBean("myBeanValidationService"
                , MyBeanValidationService.class);

        Customer customer = new Customer();
        customer.setFirstName("123");
        customer.setLastName("456");
        customer.setCustomerType(CustomerType.INDIVIDUAL);

        validateCustomer(customer, beanValidationService);

    }

    private static void validateCustomer(Customer customer,
                                         MyBeanValidationService myBeanValidationService) {
        Set<ConstraintViolation<Customer>> violations =
                new HashSet<ConstraintViolation<Customer>>();
        violations = myBeanValidationService.validateCustomer(customer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
        System.out.println("No. of violations: " + violations.size());
        for (ConstraintViolation<Customer> violation : violations) {
            System.out.println("Validation error for property: " +
                    violation.getPropertyPath()
                    + " with value: " + violation.getInvalidValue()
                    + " with error message: " + violation.getMessage());
        }
    }
}
