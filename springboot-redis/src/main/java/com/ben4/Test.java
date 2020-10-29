package com.ben4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author: caokang
 * @Date: Created in 14:41 2020/9/7
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@Slf4j
public class Test {

    public void test(){
        for(int i=0;i<100;i++){
            test1(i);
        }
    }

    @Async
    public String test1(int i){
        log.info("本次参数如下：{}" + i);
        return String.valueOf(i);
    }

    public void main(){

    }
}
