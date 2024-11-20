package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * add
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("category - add")
    public Result save(@RequestBody CategoryDTO categoryDTO) {
        log.info("add category: {}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }


    /**
     * search by page
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("category - search by page")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("page - parameter: {}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);

    }


    /**
     * disable or enable
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("category - disable or enable")
    public Result disable(@PathVariable Integer status, Long id) {
        categoryService.disable(status, id);
        return Result.success();
    }


    /**
     * update
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("category - update")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }


    // search - category

    /**
     * search by type
     * @param type
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("category - search by type")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

    /**
     * delete
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("category - delete")
    public Result delete(Long id) {
        categoryService.deleteById(id);
        return Result.success();
    }


}
