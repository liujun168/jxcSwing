package com.swing.jxc.demo.swing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class Buy implements Serializable {
    private Long id;
    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 合同日期
     */
    private Date contractDate;

    /**
     * 型号及规格
     */
    private String model;

    /**
     * 数量
     */
    private  Long amount;

    /**
     * 实发
     */
    private Long actualSend;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总金额
     */
    private BigDecimal money;

    /**
     * 定金
     */
    private BigDecimal earnestMoney;

    /**
     * 欠款(尾款)
     */
    private BigDecimal tailMoney;

    /**
     * 付款方式;目前默认为:（月结）
     */
    private String payType="月结";

    /**
     * 发货日期
     */
    private Date sendDate;

    /**
     * 收款情况
     */
    private String receivables;

}
