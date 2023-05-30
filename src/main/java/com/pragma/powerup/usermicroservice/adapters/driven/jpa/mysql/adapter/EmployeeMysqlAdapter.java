package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.EmployeeEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IEmployeeEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IEmployeeRepository;
import com.pragma.powerup.usermicroservice.domain.model.Employee;
import com.pragma.powerup.usermicroservice.domain.spi.IEmployeePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeMysqlAdapter implements IEmployeePersistencePort {

    private final IEmployeeEntityMapper employeeEntityMapper;
    private final IEmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeSaved = employeeRepository.save(employeeEntityMapper.toEntity(employee));
        return employeeEntityMapper.toModel(employeeSaved);
    }
}
