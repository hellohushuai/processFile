package com.iflytek.processfile.service.base;

import com.iflytek.processfile.utils.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/5/7
 * @Interface: BaseService
 * @Description:
 */
public interface BaseService<T> {
    Logger logger = LoggerFactory.getLogger(BaseService.class);

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    PageBean<T> getPage(Integer pageNumber, Integer pageSize, T t);

    int deleteBatch(List<String> ids);

    T get(T t);

}
