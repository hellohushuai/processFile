package com.iflytek.processfile.dao;

import com.iflytek.processfile.dto.CyProcessFile;
import com.iflytek.processfile.dto.CyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CyUser record);

    int insertSelective(CyUser record);

    CyUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CyUser record);

    int updateByPrimaryKey(CyUser record);

}