package com.kantboot.util.core.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdminService<T extends Serializable,ID> {

    /**
     * 添加数据
     */
    T save(T entity);

    /**
     * 批量保存
     */
    void saveBatch(List<T> entityList);


    /**
     * 根据id删除
     */
    void remove(T entity);

    /**
     * 批量删除
     */
    void removeBatch(List<T> entityList);


}
