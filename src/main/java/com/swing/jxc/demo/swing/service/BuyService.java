package com.swing.jxc.demo.swing.service;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.model.Buy;

import java.util.List;

/**
 * @description: 进货接口
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:08
 */
public interface BuyService {

    /**
     * 添加记录
     * @param buy
     * @return
     */
    ResponseResult addRecord(Buy buy);

    /**
     * 修改记录
     * @param buy
     * @return
     */
    ResponseResult updateRecord(Buy buy);

    /**
     * 删除机记录
     * @param id
     * @return
     */
    ResponseResult deletedRecord(Long id);

    /**
     * 查询数据
     * @param pageVo
     * @return
     */
    List<Buy> queryList(PageVo pageVo);
}
