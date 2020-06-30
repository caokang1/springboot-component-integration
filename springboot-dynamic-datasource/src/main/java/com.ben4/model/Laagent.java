package com.ben4.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: caokang
 * @Date: Created in 10:02 2020/1/13
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@Data
@TableName("laagent")
public class Laagent {

    private String managecom;

    private String agentcode;

    private String agentnum;
}
