package com.iflytek.processfile.controller;

import com.iflytek.processfile.controller.base.BaseController;
import com.iflytek.processfile.dto.CyProcessFile;
import com.iflytek.processfile.service.CyProcessFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2018/5/7
 * @Interface: indexController
 * @Description:
 */
@Controller
public class indexController extends BaseController {

    @Autowired
    CyProcessFileService cyProcessFileService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping({"/","index.act"})
    public String index(Model model){
        //获取浏览次数最多的6个流程文件
        List<CyProcessFile> processFileList = cyProcessFileService.getNumProcessFileList(1000);
        model.addAttribute("processFileList",processFileList);
        return "index.html";
    }

    @RequestMapping("management.act")
    public String management(){
        return "management.html";
    }

    @RequestMapping("processFileList.act")
    public String processFileList(){
        return "processFileList.html";
    }

    @RequestMapping("addProcessFile.act")
    public String addProcessFile(){
        return "addProcessFile.html";
    }

    @RequestMapping("updateProcessFile.act")
    public String updateProcessFile(CyProcessFile cyProcessFile,Model model){
        CyProcessFile curProcessFile = cyProcessFileService.get(cyProcessFile);
        model.addAttribute("curProcessFile",curProcessFile);
        return "updateProcessFile.html";
    }
}
