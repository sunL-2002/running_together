package com.yu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yu.constant.MessageConstant;
import com.yu.constant.PasswordConstant;
import com.yu.constant.StatusConstant;
import com.yu.context.BaseContext;
import com.yu.dto.EmployeeDTO;
import com.yu.dto.EmployeeLoginDTO;
import com.yu.dto.EmployeePageQueryDTO;
import com.yu.entity.Employee;
import com.yu.exception.AccountLockedException;
import com.yu.exception.AccountNotFoundException;
import com.yu.exception.PasswordErrorException;
import com.yu.mapper.EmployeeMapper;
import com.yu.result.PageResult;
import com.yu.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对我们前端传来的明文密码进行md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password+"-----------------");
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     * @param employeeDTO
     */
    public void sava(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        //设置属性拷贝
        BeanUtils.copyProperties(employeeDTO,employee);

        //设置账号的状态，默认正常状态，1：正常  0：禁用
        employee.setStatus(StatusConstant.ENABLE);

        //设置密码，默认密码123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置当前记录的创建时间和修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置当前创建人ID和修改人ID
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //select * from employee limit 0,10
        //开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);

        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult(total,records);
    }

    /**
     * 编辑员工信息
     * @param employeeDTO
     */
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.update(employee);
    }

    /**
     * 启用、禁用员工账号
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        //update employee set status = ? and id = ?

        /*Employee employee = new Employee(); //1.传统写法
        employee.setStatus(status);
        employee.setId(id);*/

        Employee employee = Employee.builder() //2.根据@build注解，实现相同效果
                .status(status)
                .id(id)
                .build();

        employeeMapper.update(employee);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("****");//前端密码显示****
        return employee;
    }
}
