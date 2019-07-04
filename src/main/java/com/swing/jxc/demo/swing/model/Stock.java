package com.swing.jxc.demo.swing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Stock implements Serializable {
    private Long id;

    /**
     * 型号
     */
    private String modelName;

    /**
     * 采购数量
     */
    private Long sellNum;

    /**
     * 出库数量
     */
    private Long buyNum;

    /**
     * 库存量
     */
    private Long stockNum;
}
