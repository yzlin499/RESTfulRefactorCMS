package com.zhbit.cms.frame;

import org.dom4j.Element;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class PermissionTree {
    private static final int NO_LEVEL = 0x7fffffff;
    private String pathMatches;
    private PermissionTree[] children;
    private int[] levels;

    PermissionTree(Element element) {
        pathMatches = Objects.toString(element.attributeValue("name"), "/.*") + "/?";
        parseInterface(element);
        parseUserGroup(element);
    }

    /**
     * 解析接口
     *
     * @param element
     */
    private void parseInterface(Element element) {
        List<Element> userGroups = element.elements("Interface");
        children = userGroups.stream().map(PermissionTree::new).toArray(PermissionTree[]::new);
    }

    /**
     * 解析权限
     *
     * @param element
     */
    private void parseUserGroup(Element element) {
        List<Element> userGroups = element.elements("UserGroup");
        if (userGroups.size() == 0) {
            levels = new int[0];
            return;
        }
        levels = new int[Integer.parseInt(userGroups.get(userGroups.size() - 1).attributeValue("name")) + 1];
        Arrays.fill(levels, NO_LEVEL);
        //解析权限
        for (Element ug : userGroups) {
            try {
                levels[Integer.parseInt(ug.attributeValue("name"))] = Integer.parseInt(ug.attributeValue("minctrllevel"));
            } catch (ArrayIndexOutOfBoundsException e) {
                int newLength = Integer.parseInt(ug.attributeValue("name")) + 1;
                int[] newLevels = Arrays.copyOf(levels, newLength);
                for (int i = levels.length; i < newLength; i++) {
                    newLevels[i] = NO_LEVEL;
                }
                levels = newLevels;
                levels[newLength - 1] = Integer.parseInt(ug.attributeValue("minctrllevel"));
            }
        }
    }

    /**
     * 获取权限
     *
     * @param group
     * @param pathInfo
     * @return
     */
    int getLevel(int group, String pathInfo) {
        if (children.length == 0) {
            return group < levels.length ? levels[group] : NO_LEVEL;
        } else {
            for (PermissionTree c : children) {
                if (pathInfo.matches(c.pathMatches)) {
                    int p = c.getLevel(group, pathInfo);
                    return p == NO_LEVEL && group < levels.length ? levels[group] : p;
                }
            }
            return group < levels.length ? levels[group] : NO_LEVEL;
        }
    }
}
