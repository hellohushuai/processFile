package com.iflytek.processfile.dao;

import com.iflytek.processfile.dto.CyProcessFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @class_name: CyProcessFileMapper
 * @package: com.iflytek.processfile.dao
 * @describe: TODO
 * @author: shuaihu2
 * @creat_date: 2018/5/8
 * @creat_time: 9:49
 **/
@Mapper
public interface CyProcessFileMapper {


    CyProcessFile get(CyProcessFile processFile);

    int deleteByPrimaryKey(Long id);

    int insert(CyProcessFile record);

    int insertSelective(CyProcessFile record);

    CyProcessFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CyProcessFile record);

    int updateByPrimaryKey(CyProcessFile record);

    /**通过title查询获得流程文件*/
    List<CyProcessFile> fingByTitle(@Param("title")String title);

    /**得到当前表中流程文件中id最大值*/
    Long maxId();

    /*查询指定数量流程文件*/
    List<CyProcessFile> getCountProcessFile(@Param("count") int count);

    Integer getCount();

    List<CyProcessFile> getList(CyProcessFile t);

    List<CyProcessFile> getList();

    /**
     * codeTODO:15:02
     * describe: 需要修改添加 @Param("loginName")String loginName,
     * user: shuaihu2
     */
    Integer deleteBatch(@Param("list")List<String> list,
                         @Param("updatetime")Date updatetime);
}