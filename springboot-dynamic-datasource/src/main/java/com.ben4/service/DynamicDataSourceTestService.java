package com.ben4.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ben4.dao.MasterDao;
import com.ben4.dao.SlaveDao;
import com.ben4.framework.annotation.DataSource;
import com.ben4.model.Laagent;
import com.ben4.model.VSubmitedPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试多数据源
 * <p>
 *
 * @Author: caokang
 */
@Service
@Slf4j
public class DynamicDataSourceTestService {

    @Autowired
    SlaveDao slaveDao;

    @Autowired
    MasterDao masterDao;

    public StringBuffer selectLaagentList1() {
        StringBuffer stringBuffer = new StringBuffer();
        List<Laagent> laagentList = masterDao.selectList(new QueryWrapper<>());
        stringBuffer.append(laagentList.get(0));
        log.info("默认数据源数据：" + stringBuffer.toString());
        return stringBuffer;
    }

    @DataSource("slave1")
    public StringBuffer selectLaagentList2() {
        StringBuffer stringBuffer = new StringBuffer();
        List<Laagent> laagentList = masterDao.selectList(new QueryWrapper<>());
        stringBuffer.append(laagentList.get(0));
        log.info("主数据源数据：" + stringBuffer.toString());
        return stringBuffer;
    }

    @DataSource("slave2")
    public StringBuffer selectVSubmitedPolicyList() {
        StringBuffer stringBuffer = new StringBuffer();
        List<VSubmitedPolicy> vSubmitedPolicyList = slaveDao.selectList(new QueryWrapper<>());
        stringBuffer.append(vSubmitedPolicyList.get(0));
        log.info("副数据源数据：" + stringBuffer.toString());
        return stringBuffer;
    }
}