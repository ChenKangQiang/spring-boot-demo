package edu.tongji.comm.mapper;

import edu.tongji.comm.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: chenkangqiang
 * @Date: 2019/9/14
 */
public interface UserMapper {

    void insert(@Param("entity") UserEntity entity);

    UserEntity load(@Param("id") int id);

}
