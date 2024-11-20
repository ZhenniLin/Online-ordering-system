package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * insert category
     * @param category
     */
    @Insert("insert into sky_take_out.category (id, type, name, sort, status, create_time, update_time, create_user, update_user)" +
            "values" + "(#{id}, #{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);


    /**
     * search by page
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * update
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);


    /**
     * select by type
     * @param type
     * @return
     */
    List<Category> list(Integer type);

    /**
     * delete by id
     * @param id
     */
    void deleteById(Long id);
}
