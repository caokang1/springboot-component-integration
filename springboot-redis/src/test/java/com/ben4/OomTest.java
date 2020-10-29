package com.ben4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: caokang
 * @Date: Created in 15:53 2020/9/21
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OomTest {

    @Test
    public void oomtest() {
        List<Byte[]> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                list.add(new Byte[1024 * 1024 * 1024]);
                i++;
            }
        } catch (Exception e) {
            log.info("=====你还能挺住几次了===== {}" + i);
            e.printStackTrace();
        } finally {
            log.info("stop the world");
        }
    }

}
