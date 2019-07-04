package com.swing.jxc.demo.swing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class TotalInfo implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 总数
     */
    private Long amount;
}
