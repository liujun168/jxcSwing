package com.swing.jxc.demo.swing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Machine implements Serializable {

    private Long  id;

    /**
     *
     * 型号名称
     */
    private String name;

    /**
     * 是否删除;(0:否，1：是)
     */
    private Boolean delFlag;
}
