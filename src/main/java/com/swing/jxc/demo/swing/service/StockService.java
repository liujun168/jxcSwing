package com.swing.jxc.demo.swing.service;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.model.Stock;

public interface StockService {

    /**
     * 查询数据
     * @param pageVo
     * @return
     */
    PageVo<Stock> queryList(PageVo pageVo);
}
