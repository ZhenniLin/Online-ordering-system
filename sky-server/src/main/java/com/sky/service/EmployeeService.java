package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * employee add
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);


    /**
     * employee search
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * enable and disable employee account
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * employ-search by id
     * @param id
     * @return
     */
    Employee getById(Long id);


    /**
     * employee-update
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
