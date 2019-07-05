package com.swing.jxc.demo.swing.service;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.model.Machine;

import java.util.List;

public interface MachineService {

    /**
     * 添加机器型号
     * @param machine
     * @return
     */
    ResponseResult addMachine(Machine machine);

    /**
     * 修改机器型号
     * @param machine
     * @return
     */
    ResponseResult updateMachine(Machine machine);

    /**
     * 删除机器型号
     * @param ids
     * @return
     */
    ResponseResult deleteMachine(List<Long> ids);

    /**
     * 查询数据
     * @param pageVo
     * @return
     */
    PageVo<Machine> queryList(PageVo pageVo);

    /**
     * 查询所有设备型号
     * @return
     */
    List<Machine> queryAll();
}
