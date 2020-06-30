package com.ben4;

import com.ben4.service.DynamicDataSourceTestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: caokang
 * @Date: Created in 17:01 2020/6/29
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DynamicDatasourceTest {

    @Autowired
    DynamicDataSourceTestService dynamicDataSourceTestService;

    @Test
    public void contextLoads() {
        log.info("============多数据源测试开始=============");
        dynamicDataSourceTestService.selectLaagentList1();
        dynamicDataSourceTestService.selectLaagentList2();
        dynamicDataSourceTestService.selectVSubmitedPolicyList();
        log.info("============多数据源测试结束=============");
    }
}
