package com.kantboot.util.core.param;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * 用来请求分页的参数的封装类
 * @author 方某方
 */
@Data
@Accessors(chain = true)
public class PageParam<T> implements Serializable {

    private Integer pageSize;

    private Integer pageNumber;

    private T data;

    public Pageable getPageable(){
        return Pageable.ofSize(pageSize).withPage(pageNumber-1);
    }

}
