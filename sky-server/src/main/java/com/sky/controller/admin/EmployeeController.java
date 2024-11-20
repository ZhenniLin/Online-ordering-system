package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * login
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "employ-login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        // 创建了一个token - 三个参数 key / time / claims
        // jwtProperties是封装的配置累
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        //封装成VO 返回给前端
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        // 把返回给前端的数据同一封装到result
        return Result.success(employeeLoginVO);
    }

    /**
     * exit
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "employee-logout")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * add
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "employee-add")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * search by page
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("employee-search by pages")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询：参数为:{}", employeePageQueryDTO);

        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);

        return Result.success(pageResult);
    }


    /**
     * enable and disable employee account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("employee-enable and disable account")
    public Result startOrStop (@PathVariable Integer status, Long id) {
        log.info("enable and disable employee account: {}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * search employ's information by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("employ-search by id")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * update employee information
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("employ-update")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("update employee information: {}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

}
