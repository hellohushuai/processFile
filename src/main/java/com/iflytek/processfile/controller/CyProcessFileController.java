package com.iflytek.processfile.controller;

import com.alibaba.fastjson.JSONObject;
import com.iflytek.processfile.controller.base.BaseController;
import com.iflytek.processfile.dto.CyProcessFile;
import com.iflytek.processfile.service.CyProcessFileService;
import com.iflytek.processfile.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/5/8
 * @Interface: CyProcessFileController
 * @Description:
 */
@Controller
public class CyProcessFileController extends BaseController {

    @Autowired
    CyProcessFileService cyProcessFileService;
    @Autowired
    HttpServletRequest request;


    /**
     * @class_name: manageUserInfoUpdate
     * @param: [cyProcessFile]
     * @describe: 新增或者修改流程文件
     * @author: shuaihu2
     * @creat_date: 2018/4/13
     * @creat_time: 8:47
     **/
    @ResponseBody
    @RequestMapping("manageProcessFileUpdate.act")
    public JSONObject manageProcessFileUpdate(CyProcessFile cyProcessFile){
        return cyProcessFileService.processFileUpdate(cyProcessFile);
    }

    /**
     * @class_name: manageProcessFileQuery
     * @param: [cyProcessFile]
     * @describe: 用于回显指定的流程文件相关信息
     * @author: shuaihu2
     * @creat_date: 2018/4/13
     * @creat_time: 8:57
     **/
    @ResponseBody
    @RequestMapping("manageProcessFileQuery.act")
    public JSONObject manageProcessFileQuery(CyProcessFile cyProcessFile){
        //得到流程文件id
        Long id = cyProcessFile.getId();
        CyProcessFile processFile = cyProcessFileService.selectByPrimaryKey(id);
        if (processFile == null) {
            //查询失败
            return this.result("1","获取数据出错或者数据为空");
        }
        return this.result("0",processFile,"查询成功");
    }

    /**
     * @class_name: manageProcessFileDelete
     * @param: [ids, model]
     * @describe: 删除流程文件（逻辑删除）
     * @author: shuaihu2
     * @creat_date: 2018/4/23
     * @creat_time: 10:15
     **/
    @ResponseBody
    @RequestMapping("manageProcessFileDelete.data")
    public JSONObject manageProcessFileDelete(String ids, Model model){
        List<String> idList = new ArrayList<>();
        String[] idArr = ids.split(",");
        for (int i =0;i<idArr.length;i++){
            if (StringUtils.isNoneBlank(idArr[i])){
                idList.add(idArr[i]);
            }
        }
        if (idList.size() > 0){
            int i = cyProcessFileService.deleteBatch(idList);
            if (i != 0){
                return this.result("0","删除成功");
            }
            return this.result("1","删除失败，请联系管理员");
        }else {
            return this.result("1","请选择要删除的权限");
        }

    }

    /**
     * @class_name: manageProcessFileCount
     * @param: []
     * @describe: 查询流程文件总数
     * @author: shuaihu2
     * @creat_date: 2018/4/23
     * @creat_time: 10:16
     **/
    @ResponseBody
    @RequestMapping("manageProcessFileCount.data")
    public JSONObject manageProcessFileCount(){
        Integer count = cyProcessFileService.getCount();
        return this.result("0",count,"查询成功！！！");
    }

    /**
     * @class_name: getProcessFileByTitle
     * @param: [model, cyProcessFile]
     * @describe: 通过标题模糊查询
     * @author: shuaihu2
     * @creat_date: 2018/6/4
     * @creat_time: 16:56
     **/
    @RequestMapping("getProcessFileByTitle")
    public String getProcessFileByTitle(Model model, CyProcessFile cyProcessFile){
        System.out.println(cyProcessFile.toString());
        cyProcessFile.setTitle("%"+cyProcessFile.getTitle()+"%");
        String title = cyProcessFile.getTitle();
        List<CyProcessFile> cyProcessFiles = cyProcessFileService.fingByTitle(title);
        model.addAttribute("processFileList",cyProcessFiles);
        return "index.html";
    }

    @RequestMapping("getProcessFileByPage")
    public JSONObject getProcessFileByPage(){

        return null;
    }

}
