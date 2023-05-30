package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IEmployeeServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.NotOwnerRestaurantException;
import com.pragma.powerup.usermicroservice.domain.model.Employee;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IEmployeePersistencePort;

public class EmployeeUseCase implements IEmployeeServicePort {

    private final IEmployeePersistencePort employeePersistencePort;
    private final IUserServicePort userServicePort;

    public EmployeeUseCase(IUserServicePort userServicePort,
                           IEmployeePersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
        this.userServicePort = userServicePort;
    }

    @Override
    public Employee saveEmployee(String dniOwner, Restaurant restaurant, Employee employee) {
        if(!restaurant.getOwnerDni().equals(dniOwner))
            throw new NotOwnerRestaurantException();

        User userSaved = userServicePort.saveEmployee(employee.getUser());
        employee.setUser(userSaved);
        return employeePersistencePort.saveEmployee(employee);
    }
}
