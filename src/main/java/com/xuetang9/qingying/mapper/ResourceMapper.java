package com.xuetang9.qingying.mapper;

import com.xuetang9.qingying.domain.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 11:43
 * @copyright 老九学堂
 */
@Repository
public interface ResourceMapper extends Mapper<Resource> {
    List<Resource> selectResourceByUserId(int userId);

    int selectCountByUrlAndUserId(@Param("url") String url,@Param("userId") int userId);
}
