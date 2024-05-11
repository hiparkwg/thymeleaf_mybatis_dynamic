package com.myjob.dynamic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MemberController {

    @Autowired
    MemberDao dao;

    @RequestMapping(path="/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(path="/member_search", method=RequestMethod.GET)
    public ModelAndView memberSearchNone(MemberVo vo){
        ModelAndView mv = new ModelAndView();
        List<MemberVo> list = new ArrayList<>();
        mv.addObject("list", list);
        mv.setViewName("member/member_search"); // templates/member/member_search.html
        return mv;
    }


    @RequestMapping(path="/member_search", method=RequestMethod.POST)
    public ModelAndView memberSearch(MemberVo vo, String findStr){
        ModelAndView mv = new ModelAndView();

        List<MemberVo> list = dao.list(findStr);
        mv.addObject("list", list);
        mv.addObject("findStr", findStr);

        mv.setViewName("member/member_search"); // templates/member/member_search.html
        return mv;
    }
    @RequestMapping(path="/member_register")
    public ModelAndView memberRegister(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("member/member_register");
        return mv;
    }
    @RequestMapping(path="/member_modify")
    public ModelAndView memberModify(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("member/member_modify"); 
        return mv;
    }
    @RequestMapping(path="/member_delete")
    public ModelAndView memberDelete(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("member/member_delete"); 
        return mv;
    }

    @RequestMapping(path="/member_view")
    public ModelAndView memberView(String id){
        ModelAndView mv = new ModelAndView();
        MemberVo vo = dao.view(id);
        mv.addObject("vo", vo);
        mv.setViewName("member/member_view"); 
        return mv;
    }



    @RequestMapping(path="/if")
    public ModelAndView ifTest(){
        ModelAndView mv = new ModelAndView();
        dao.ifTest();
        mv.setViewName("index");
        return mv;
    }
}
