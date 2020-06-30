package com.ben4.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author: caokang
 * @Date: Created in 16:47 2020/1/16
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@Data
@TableName("V_Submited_Policy")
public class VSubmitedPolicy extends Model {

    private String policyNo;

    private String tmrCode;

    private String campaignCode ;
}
