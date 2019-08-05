package com.lmp.admin.controller;

import com.lmp.admin.util.AgentHttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "lmpAgent")
public class LmpGatewayController {

    @Resource
    private AgentHttpUtil agentHttpUtil;

    @RequestMapping("commonsAgent")
    public String commonsAgent(HttpServletRequest request){
        Enumeration paramNames = request.getParameterNames();
        Map<String,String> map = new HashMap<>();
        while(paramNames.hasMoreElements()){
            String paraName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paraName);
            if(paramValues.length == 1){
                String paramValue = paramValues[0];
                map.put(paraName,paramValue);
            }
        }
       return agentHttpUtil.lmpServiceSendPost(map);
    }

    @RequestMapping(value = "noAuthority")
    public String noAuthority(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paraName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paraName);
            if(paramValues.length == 1){
                String paramValue = paramValues[0];
                map.put(paraName,paramValue);
            }
        }
        return agentHttpUtil.lmpServiceSendPost(map);
    }

}
