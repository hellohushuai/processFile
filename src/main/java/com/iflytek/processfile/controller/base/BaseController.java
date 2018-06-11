package com.iflytek.processfile.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.iflytek.processfile.dto.CyProcessFile;
import com.iflytek.processfile.dto.CyUser;
import com.iflytek.processfile.utils.Constant;
import com.iflytek.processfile.utils.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/5/8
 * @Interface: BaseController
 * @Description:
 */
public class BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);

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
    //生成MD5
    public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    // 二进制转十六进制
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
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

    public CyProcessFile changeUrl(CyProcessFile processFile){
        /**
         * codeTODO:10:58
         * describe: 生成baseUrl 并拼接url
         * user: shuaihu2
         */
        String baseUrl = "localhost:8090/processfile";
        System.out.println(baseUrl);
        String url = processFile.getUrl();
        if (url == null) {
            return processFile;
        }
        processFile.setUrl(baseUrl+url);
        return processFile;
    }

    public PageBean<CyProcessFile> changeListUrl(PageBean<CyProcessFile> page){
        List<CyProcessFile> processFiles = new ArrayList<CyProcessFile>();
        List<CyProcessFile> list = page.getList();
        for (int i = 0; i < list.size(); i++) {
            CyProcessFile cyProcessFile = changeUrl(list.get(i));
            processFiles.add(cyProcessFile);
        }
        page.setList(processFiles);
        return page;
    }
}
