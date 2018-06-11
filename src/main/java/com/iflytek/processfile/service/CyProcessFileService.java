package com.iflytek.processfile.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iflytek.processfile.dao.CyProcessFileMapper;
import com.iflytek.processfile.dao.CyUserMapper;
import com.iflytek.processfile.dto.CyProcessFile;
import com.iflytek.processfile.dto.CyUser;
import com.iflytek.processfile.utils.Constant;
import com.iflytek.processfile.utils.PageBean;
import com.iflytek.processfile.utils.RemoveHtml;
import com.iflytek.processfile.utils.XSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.iflytek.processfile.utils.RemoveHtml.toRemoveHtml;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/5/7
 * @Interface: CyProcessFileService
 * @Description:
 */
@Service
public class CyProcessFileService  {
    Logger logger = LoggerFactory.getLogger(CyProcessFileService.class);

    @Autowired
    public CyProcessFileMapper cyProcessFileMapper;

    @Autowired
    public CyUserMapper cyUserMapper;

    @Autowired
    public HttpServletRequest request;


    public int deleteByPrimaryKey(Long id) {
        Integer integer = 0;
        try {
            integer = cyProcessFileMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("删除单个文件报错！！id："+id,e);
        }
        return 0;
    }


    public int insert(CyProcessFile record) {
        Integer insert = 0;
        try {
            insert = cyProcessFileMapper.insert(record);
        } catch (Exception e) {
            logger.error("修改流程文件报错！！record.getTitle="+record.getTitle(),e);
        }
        return insert;
    }


    public int insertSelective(CyProcessFile record) {
        return 0;
    }


    public CyProcessFile selectByPrimaryKey(Long id) {
        CyProcessFile cyProcessFile = null;
        try {
            cyProcessFile = cyProcessFileMapper.selectByPrimaryKey(id);
            if(cyProcessFile.getUid() != null){
                CyUser user = cyUserMapper.selectByPrimaryKey(cyProcessFile.getUid());
                cyProcessFile.setRealName(user.getRealName());
            }
        } catch (Exception e) {
            logger.error("获取流程文件失败！！");
            e.printStackTrace();
        }
        return cyProcessFile;
    }


    public int updateByPrimaryKeySelective(CyProcessFile record) {
        Integer insert = 0;
        try {
            insert = cyProcessFileMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error("新增流程文件报错！！",e);
        }
        return insert;
    }


    public int updateByPrimaryKey(CyProcessFile record) {
        return 0;
    }


    public PageBean<CyProcessFile> getPage(Integer pageNumber, Integer pageSize, CyProcessFile cyProcessFile) {
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, pageSize == null ? Integer.MAX_VALUE : pageSize);
        if (StringUtils.isNoneBlank(cyProcessFile.getTitle())){
            cyProcessFile.setTitle("%"+cyProcessFile.getTitle()+"%");
        }
        List<CyProcessFile> processFiles = null;
        try {
            processFiles = cyProcessFileMapper.getList(cyProcessFile);
            Iterator<CyProcessFile> it =  processFiles.iterator();
            while(it.hasNext()){
                CyProcessFile cp = it.next();
                String title = cp.getTitle();
                String title2 = toRemoveHtml(title);
                cp.setTitle(title2);
                CyUser user = cyUserMapper.selectByPrimaryKey(cp.getUid());
                cp.setRealName(user.getRealName());
            }
        } catch (Exception e) {
            logger.error("查询流程文件报错！！pageNumber:"+pageNumber+"pageSize:"+pageSize,e);
        }
        PageBean<CyProcessFile> page = new PageBean<>(processFiles);

        return page;
    }


    public PageBean<CyProcessFile> getXSSPage(Integer pageNumber, Integer pageSize, CyProcessFile cyProcessFile) {
        PageHelper.startPage(pageNumber == null ? 1 : pageNumber, pageSize == null ? Integer.MAX_VALUE : pageSize);
        if (StringUtils.isNoneBlank(cyProcessFile.getTitle())){
            cyProcessFile.setTitle("%"+cyProcessFile.getTitle()+"%");
        }
        List<CyProcessFile> processFiles = null;
        try {
            processFiles = cyProcessFileMapper.getList(cyProcessFile);
            Iterator<CyProcessFile> it =  processFiles.iterator();
            while(it.hasNext()){
                CyProcessFile cp = it.next();
                cp.setDescription(XSSUtils.stripHtml(cp.getDescription()));
                CyUser user = cyUserMapper.selectByPrimaryKey(cp.getUid());
                cp.setRealName(user.getRealName());
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cp.getStartTime());
                cp.setStartTimeString(dateStr);
            }
        } catch (Exception e) {
            logger.error("查询流程文件报错！！pageNumber:"+pageNumber+"pageSize:"+pageSize,e);
        }
        PageBean<CyProcessFile> page = new PageBean<>(processFiles);

        return page;
    }


    public int deleteBatch(List<String> ids) {
        /**
         * codeTODO:15:03
         * describe: 需要添加参数 currentUser.getLoginName(),
         * user: shuaihu2
         */
//        CyUser currentUser = (CyUser) request.getSession().getAttribute(Constant.CURRENTUSER);
        Integer res = 0;
        try {
            res = cyProcessFileMapper.deleteBatch(ids,  new Date());
        } catch (Exception e) {
            logger.error("流程文件批量删除报错！！id范围："+ids.size(),e);
        }
        return res;
    }


    public CyProcessFile get(CyProcessFile cyProcessFile) {
        CyProcessFile processFile = null;
        try {
            processFile = cyProcessFileMapper.get(cyProcessFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processFile;
    }

    /**
     * @class_name: fingByTitle
     * @param: [title]
     * @describe: 通过标题获取对应的流程文件
     * @author: shuaihu2
     * @creat_date: 2018/4/16
     * @creat_time: 18:58
     **/
    public List<CyProcessFile> fingByTitle(String title){
        List<CyProcessFile> cyProcessFiles = null;
        try {
            cyProcessFiles = cyProcessFileMapper.fingByTitle(title);
            if (cyProcessFiles.size()==0){
                logger.info("查询不到对应的流程文件！");
                return null;
            }else if (cyProcessFiles.size()==1){
                logger.info("查询到一个指定的流程文件！");
                Iterator<CyProcessFile> it =  cyProcessFiles.iterator();
                while(it.hasNext()){
                    CyProcessFile cp = it.next();
                    cp.setTitle(XSSUtils.stripHtml(cp.getTitle()));
                    cp.setDescription(XSSUtils.stripHtml(cp.getDescription()));
                }
                return cyProcessFiles;
            }else{
                logger.info("查询到多个个指定的流程文件！");
                Iterator<CyProcessFile> it =  cyProcessFiles.iterator();
                while(it.hasNext()){
                    CyProcessFile cp = it.next();
                    cp.setTitle(XSSUtils.stripHtml(cp.getTitle()));
                    cp.setDescription(XSSUtils.stripHtml(cp.getDescription()));
                }
                return cyProcessFiles;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @class_name: getCount
     * @param: []
     * @describe: 获取当前流程文件总数
     * @author: shuaihu2
     * @creat_date: 2018/4/16
     * @creat_time: 18:59
     **/
    public Integer getCount(){
        Integer count = null;
        try {
            count = cyProcessFileMapper.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
        }
        return count;
    }

    public JSONObject processFileUpdate(CyProcessFile cyProcessFile) {
        //获取当前用户
//        CyUser currentUser = this.getCurrentUser();
        CyUser currentUser = new CyUser();
        currentUser.setId(1234L);
        if (StringUtils.isBlank(cyProcessFile.getTitle())) {
            return this.result("1","流程文件标题不能为空！！");
        }
        CyProcessFile cyProcessFile1 = (CyProcessFile) this.fingByTitle(cyProcessFile.getTitle());
        if (cyProcessFile1 == null) {
            //得到当前域名
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
            //数据库中没有指定的流程文件   新建文件
            cyProcessFile.setDelFlag("0");
            cyProcessFile.setType("1");
            cyProcessFile.setUid(currentUser.getId());
            cyProcessFile.setStartTime(new Date());
            cyProcessFile.setPageView(0);
            cyProcessFile.setPraise(0);
            Long id = null;
            try {
                id = cyProcessFileMapper.maxId()+1;
            } catch (Exception e) {
                logger.error("数据库中没有数据！！！");
                id = 1L;
            }
            cyProcessFile.setUrl("/getProcessFileById.html?id="+id);
            int insert = this.insert(cyProcessFile);
            if (insert == 0){
                return this.result("1","添加流程文件失败,请联系管理员");
            }
            return this.result("0","添加流程文件成功！");
        }else{
            //数据库中已有该流程文件  更新
            cyProcessFile1.setUpdateUser(currentUser.getRealName());
            cyProcessFile1.setUpdateTime(new Date());
            cyProcessFile1.setDescription(cyProcessFile.getDescription());
            cyProcessFile1.setTitle(cyProcessFile.getTitle());
            int update = this.updateByPrimaryKeySelective(cyProcessFile1);
            if (update == 0 ){
                return this.result("1","修改流程文件失败,请联系管理员");
            }
            return this.result("0","修改流程文件成功！");
        }
    }


    public JSONObject result(String code, Object data, String msg){
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", msg);
        object.put("data", data);
        return object;
    }
    public JSONObject result(String code,String msg){
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", msg);
        return object;
    }
    public JSONObject result(String code,String msg,long count,long pageSize,long current,Object data){
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", msg);
        object.put("count", count);
        object.put("pageSize", pageSize);
        object.put("current", current);
        object.put("data", data);
        return object;
    }


    /**
     * 不使用参数方式而直接获取session
     *
     * @return
     */
    protected HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
            logger.error("通过getSession方法获取session失败！");
        }
        return session;
    }

    /**
     * 不使用参数方式而直接获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
    public CyUser getCurrentUser(){
        CyUser user = (CyUser) getSession().getAttribute(Constant.CURRENTUSER);
        return user;
    }
    /**获取首页所需n个流程文件*/
    public List<CyProcessFile> getNumProcessFileList(int max) {
        int count = this.getCount();
        List<CyProcessFile> list = null;
        PageBean<CyProcessFile> pageBean = null;
        if(count>=max){
            list = cyProcessFileMapper.getCountProcessFile(max);
        }else{
            list = cyProcessFileMapper.getCountProcessFile(count);
        }
        /*XSSUtils.stripHtml*/
        Iterator<CyProcessFile> it =  list.iterator();
        while(it.hasNext()){
            CyProcessFile cp = it.next();
            cp.setTitle(XSSUtils.stripHtml(cp.getTitle()));
            cp.setDescription(XSSUtils.stripHtml(cp.getDescription()));
        }

        return list;
    }

}
