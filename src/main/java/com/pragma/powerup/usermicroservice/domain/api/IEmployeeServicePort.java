package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.Employee;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;

public interface IEmployeeServicePort {

    Employee saveEmployee(String dniOwner, Restaurant restaurant, Employee employee);
}
