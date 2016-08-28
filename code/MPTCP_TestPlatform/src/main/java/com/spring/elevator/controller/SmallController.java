package com.spring.elevator.controller;


import com.spring.elevator.model.POJO.PageData;
import com.spring.elevator.model.POJO.User;
import com.spring.elevator.model.TestJson;
import com.spring.elevator.service.IService.IPageDataService;
import com.spring.elevator.service.IService.LoginAndRegister;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by AlexJIANG on 12/18/15.
 */
@Controller
public class SmallController {

    private static final Logger logger = Logger.getLogger(SmallController.class);

//
//    @Autowired
//    UserService userService;
//    @Autowired
//    AddressService addressService;
    @Autowired
    LoginAndRegister lgrg;
    @Autowired
    IPageDataService pdService;

//    @ModelAttribute
//    public void getUser(Map<String,Object> map){
//        System.out.println("model work");
//        logger.debug("here");
//        VUser vuser=new VUser();
//        System.out.println(vuser.getBirthday());
//        //map.put("vuser",vuser);
//    }

//    @RequestMapping("/")
//    public String defaultRD(){
//        return "redirect:/static/index.html";
//    }

//    @RequestMapping("/")
//    public String welcomePage(){
//        return "redirect:/static/index.html";
//    }

    @RequestMapping("/loadPage")
    public String testPage(){
        //registry.setCachePeriod(2);

        return "html/test.html";
    }

    @ResponseBody
    @RequestMapping(value="/justjson", method = POST)
    public List<TestJson> hello(@RequestParam(value="name") String name,
                                @RequestParam(value = "age",defaultValue = "0") Integer age){
        System.out.println("hello world");
        TestJson test= new TestJson("hah",0);
        System.out.println("really here!");
        List list=new ArrayList<TestJson>();
        list.add(new TestJson("hah",1));
        list.add(new TestJson("mm",2));
        list.add(new TestJson(name,age));
        return list;
    }
    @ResponseBody
    @RequestMapping(value="/purejustjson", method = GET)
    public List<TestJson> hello(){
        System.out.println("hello world");
        TestJson test= new TestJson("hah",0);
        System.out.println("really here!");
        List list=new ArrayList<TestJson>();
        list.add(new TestJson("hah",1));
        list.add(new TestJson("mm",2));
        return list;
    }
    @RequestMapping(value="angularjson",method = GET)
    public String toAngular(){
        return "/jsp/index.jsp";
    }

    @RequestMapping(value="rd",method = GET)
    public String rd(){
        return "redirect:/static/index.html";

    }
    @ResponseBody
    @RequestMapping(value="testPOJO", method = POST)
    public String matchPOJO(User user){
        List list=new ArrayList<User>();
        list.add(user);
        lgrg.register(user);
        return null;

    }
    @RequestMapping("/testMAV")
    public ModelAndView testMAV(){
        ModelAndView mav =new ModelAndView("index");
        mav.addObject("time",new Date());
        return mav;

    }

    @RequestMapping(value = "/postData",method = POST)
    public String add_data(@RequestBody PageData pd){
         //System.out.println(pd.getLoadTime()+"@"+pd.getTimestamp());
        logger.info(pd.getLoadTime()+"@"+pd.getTimestamp()+"@"+pd.getTotalTimes()+"@"+pd.getCounter());
        if(pd.getTimestamp()!=null&&pd.getLoadTime()!=null){
            pdService.save(pd);
        }
        return null;
    }
    @RequestMapping(value = "/testResult")
    public String toTestResult(){
        return "/html/showdata.html";
    }
    @ResponseBody
    @RequestMapping(value = "/showData", method = GET)
    public List<PageData> get_data(){
        return pdService.getAllPageData();
    }








}


