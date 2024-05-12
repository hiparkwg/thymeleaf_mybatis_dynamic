package com.myjob.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.myjob.dynamic.mybatis.MyFactory;

@Component
public class DynamicDao {

    SqlSession session;
    public DynamicDao(){
        session = MyFactory.getSession();
    }

 
    public String ifTest(DynamicVo vo){
        int cnt  = session.insert("dynamic.if", vo);
        String msg = "";
        if(cnt>0){
            msg = "정상이요~";
            session.commit();
        }else{
            msg = "비 정상이요~";
            session.rollback();
        }
        return msg;
    }
    
    public Map chooseTest(DynamicVo vo){
        Map<String, Object> map = new HashMap<>();
        List<DynamicVo>  list = session.selectList("dynamic.choose", vo);
        String msg = "검색됨.";
        map.put("msg", msg);
        map.put("list", list);
        return map;
    }

    public boolean foreachTest(String id, String[] att){
        boolean b=false;
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("array", att);

        int cnt = session.insert("dynamic.foreach", map);
        if(cnt>0) {
            b=true;
            session.commit();
        }else{
            session.rollback();
        }            
        return b;
    }

    public List<DynamicVo> trimTest(String temp){
        String[] findStr = temp.split(",| ");
        for(int i=0 ; i<findStr.length ; i++) findStr[i]=findStr[i].trim();

        List<DynamicVo> list = session.selectList("dynamic.trim", findStr);
        System.out.println("dao size : " + list.size());
        return list;
    }
}
