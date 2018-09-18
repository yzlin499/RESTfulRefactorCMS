package com.zhbit.cms.servlet;

import com.zhbit.cms.infobeans.IdentifyingCodeInfo;
import com.zhbit.cms.tools.IdentifyingCodeTool;
import com.zhbit.cms.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
public class IdentifyingCode {
    static final String IDENTIFYING_CODE = "identifying_code";

    private IdentifyingCodeTool identifyingCodeTool;

    @Autowired
    public void setIdentifyingCodeTool(IdentifyingCodeTool identifyingCodeTool) {
        this.identifyingCodeTool = identifyingCodeTool;
    }

    @RequestMapping("/identifyingcode")
    public void sendIdentifyingCode(HttpSession httpSession, PrintWriter out) {
        IdentifyingCodeInfo codeInfo = identifyingCodeTool.getIdentifyingCode();
        httpSession.setAttribute(IDENTIFYING_CODE, Tools.MD5(codeInfo.getContent().toLowerCase()));
        out.print(codeInfo.getImg());
        out.close();
    }

}
