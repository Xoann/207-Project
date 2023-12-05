package com.gptchathelper.data_access;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gptchathelper.pojo.ChathistoryPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface ChathistoryDao extends BaseMapper<ChathistoryPojo> {

    @Select("SELECT * FROM chat_history ORDER BY time DESC LIMIT 1")
    Optional<ChathistoryPojo> findLatestMessage();
}
