package com.swing.jxc.demo.swing.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * bootstrap 分页数据
 *
 * @Author liujun
 * @Date 2018/11/29 11:08
 */
@Setter
@Getter
@ToString
public class PageVo<T> implements Serializable {
    // -- 分页参数 --//
    /**
     * 每页大小
     */
    private Integer pageSize;
//    /**
//     * 总数
//     */
//    private Integer totalRows = 0;
    /**
     * 实体类集合
     */
    private List<T> rows = new ArrayList<T>();
    //数据总条数
    private Integer total = 0;

    /**
     * 页码
     */
    private Integer page = 1;

    private Integer offset;

    //查询参数
    private Map<String, Object> parameterMap;

    private String parameter;

//
//    private void setOffset(){
//        this.offset=(page - 1) * pageSize;
//    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return pageSize;
    }
//
//    public void setPageSize(Integer pageSize) {
//        this.pageSize = pageSize;
//    }

    //分页查询，位移起始位置
    public Integer getOffset() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return (page - 1) * pageSize;
    }
}
