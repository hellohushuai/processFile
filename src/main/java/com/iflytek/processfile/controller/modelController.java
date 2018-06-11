package com.iflytek.processfile.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.processfile.controller.base.BaseController;
import com.iflytek.processfile.dto.CyProcessFile;
import com.iflytek.processfile.service.CyProcessFileService;
import com.iflytek.processfile.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/6/1
 * @Interface: modelController
 * @Description:
 */
@Controller
@RequestMapping("/model")
public class modelController extends BaseController {
    @Autowired
    CyProcessFileService cyProcessFileService;

    @RequestMapping("/allProcessFiles")
    @ResponseBody
    public String getAllProcessFiles(Integer offset, Integer limit , String title, String type){
        System.out.println(offset);
        CyProcessFile cyProcessFile = new CyProcessFile();
        JSONObject json = new JSONObject();
        cyProcessFile.setTitle(title);
        cyProcessFile.setType(type);
        PageBean<CyProcessFile> page = cyProcessFileService.getPage(offset, limit, cyProcessFile);
        json.put("rows",page.getList());
        json.put("total",page.getTotal());
        json.put("page",page.getPageNum());
//        JSON.toJSONString(rows)
        return json.toJSONString();
    }


}
