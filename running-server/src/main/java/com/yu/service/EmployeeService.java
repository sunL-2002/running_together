package com.yu.service;

import com.yu.dto.EmployeeDTO;
import com.yu.dto.EmployeeLoginDTO;
import com.yu.dto.EmployeePageQueryDTO;
import com.yu.entity.Employee;
import com.yu.result.PageResult;


public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void sava(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 启用、禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Employee getById(Long id);
}
