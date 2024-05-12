package com.myjob.dynamic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class DynamicController {

    @Autowired
    DynamicDao dao;

    @RequestMapping(path="/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(path="/if")
    public ModelAndView ifTest(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("if");
        return mv;
    }

    @RequestMapping(path="/if_result")
    public ModelAndView ifTestResult(DynamicVo vo){
        ModelAndView mv = new ModelAndView();
        String msg = dao.ifTest(vo);
        mv.addObject("msg", msg);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(path="/choose")
    public ModelAndView chooseTest(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("choose");
        return mv;
    }
    
    @RequestMapping(path="/choose_result")
    public ModelAndView chooseTestResult(String findStr, String flag){
        ModelAndView mv = new ModelAndView();

        DynamicVo vo = new DynamicVo();
        switch(flag){
            case "name":
                vo.setName(findStr);
                break;
            case "phone":
                vo.setPhone(findStr);
                break;
            case "address1":
                vo.setAddress1(findStr);
                break;
        }

        Map map = dao.chooseTest(vo);

        mv.addObject("msg", map.get("msg"));
        mv.addObject("list", map.get("list"));
        mv.setViewName("choose_result");
        return mv;
    }


    @RequestMapping(path="/foreach")
    public ModelAndView foreachTest(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("foreach");
        return mv;
    }


    @RequestMapping(path="/foreach_result")
    public ModelAndView foreachResult(String id, String[] att){
        ModelAndView mv = new ModelAndView();
        String msg = "처리됨.";
        
        System.out.println("id:" + id);
        for(String s : att){
            System.out.println(s);
        }

        boolean b = dao.foreachTest(id, att);
        if( !b) msg="오류 발생";

        mv.addObject("msg", msg);

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(path="/trim")
    public ModelAndView trimTest(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("trim");
        return mv;
    }
    
    @RequestMapping(path="/trim_result")
    public ModelAndView trimResult(String findStr){
        ModelAndView mv = new ModelAndView();
        List<DynamicVo> list = dao.trimTest(findStr);
        mv.addObject("list", list);
        mv.setViewName("trim_result");
        return mv;
    }
    


}
