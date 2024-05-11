package com.myjob.dynamic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.myjob.dynamic.mybatis.MyFactory;

@Component
public class MemberDao {

    SqlSession session;
    public MemberDao(){
        session = MyFactory.getSession();
    }

    public List<MemberVo> list(MemberVo vo){
        List<MemberVo> list;
        String findStr = vo.getId();
        list = session.selectList("dynamic.list", findStr);

        System.out.println(list.size());
        return list;
    
    }

    public void ifTest(){
        List<MemberVo> list = session.selectList("dynamic.if", 1);
        System.out.println(list.size()); //10개
        list = session.selectList("dynamic.if", -1);
        System.out.println(list.size()); // 100개
    }
    
}
