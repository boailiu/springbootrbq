package com.boai.springbootrbq.Mapper;

import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    void saveLog(@Param("message") String message);
}
