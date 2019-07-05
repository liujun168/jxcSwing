package com.swing.jxc.demo.swing.service;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.model.Sell;

import java.util.List;

public interface SellService {

    /**
     * 添加记录
     * @param sell
     * @return
     */
    ResponseResult addRecord(Sell sell);

    /**
     * 修改记录
     * @param sell
     * @return
     */
    ResponseResult updateRecord(Sell sell);

    /**
     * 删除机记录
     * @param ids
     * @return
     */
    ResponseResult deletedRecord(List<Long> ids);

    /**
     * 查询数据
     * @param pageVo
     * @return
     */
    List<Sell> queryList(PageVo pageVo);
}
