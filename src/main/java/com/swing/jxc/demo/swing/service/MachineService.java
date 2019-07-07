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
     * @param id
     * @return
     */
    ResponseResult deleteMachine(Long id);

    /**
     * 查询数据
     * @param pageVo
     * @return
     */
    List<Machine> queryList(PageVo pageVo);

    /**
     * 查询所有设备型号
     * @param pageVo
     * @return
     */
    List<Machine> queryAll(PageVo pageVo);
}
