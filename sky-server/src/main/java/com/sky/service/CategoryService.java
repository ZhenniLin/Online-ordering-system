package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;


public interface CategoryService {

    /**
     * category - add
     * @param categoryDTO
     * @return
     */
    void save(CategoryDTO categoryDTO);


    /**
     * search by page
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * disable or enable
     * @param status
     */
    void disable(Integer status, Long id);

    /**
     * update
     * @param categoryDTO
     * @return
     */
    void update(CategoryDTO categoryDTO);

    /**
     * search by type
     * @param type
     * @return
     */
    List<Category> list(Integer type);

    /**
     * delete
     * @param id
     * @return
     */
    void deleteById(Long id);
}
