package com.zhbit.cms.frame;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;

import java.io.IOException;


public class PermissionsParsing {
    private PermissionTree permissionTree;

    public PermissionsParsing(Resource configLocation) {
        try {
            Document document = new SAXReader().read(configLocation.getFile());
            permissionTree = new PermissionTree(document.getRootElement());
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    public int getLevel(int userGroup, String pathInfo) {
        return permissionTree.getLevel(userGroup, pathInfo);
    }


}
