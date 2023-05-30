package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.Employee;

public interface IEmployeePersistencePort {

    Employee saveEmployee(Employee employee);
}
