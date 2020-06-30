package com.ben4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ben4.model.VSubmitedPolicy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: caokang
 * @Date: Created in 15:25 2020/1/21
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@Mapper
public interface SlaveDao extends BaseMapper<VSubmitedPolicy>{
}
