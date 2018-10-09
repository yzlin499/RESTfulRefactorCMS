package com.zhbit.cms.wechat.realization;

import com.zhbit.cms.dao.TermDAO;
import com.zhbit.cms.infobeans.wechat.WCText;
import com.zhbit.cms.wechat.WeChatIO;
import com.zhbit.cms.wechat.event.WCTextEvent;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WCSelectRoom implements WCTextEvent<WCSelectRoom.SelectKey> {
    private Pattern pattern = Pattern.compile("查询(?<day>[\\u4E00-\\u9FA5])天(?<building>[\\u4E00-\\u9FA5]{2})楼(?<isUsed>(占用)|(空闲))课室");
    ;
    private List<Character> dayList = Arrays.asList('前', '昨', '今', '明', '后');
    private Set<String> buildSet = new HashSet<>(Arrays.asList("全部", "知行", "明德", "弘毅", "天佑", "弘毅", "艺悦", "精工"));
    private Calendar startDay;

    public WCSelectRoom(TermDAO termDAO) {
        startDay = Calendar.getInstance();
        startDay.setTime(termDAO.nowTerm());
    }

    public static String convertBitFlagToStr(int SelectValue, int maxBit) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        int first = 0;
        int last = 0;
        SelectValue &= ((1 << maxBit - 1) - 1);
        while (SelectValue != 0) {
            if ((SelectValue & 1) == 0) {
                if (first != 0) {
                    if (last == first || last == 0) {
                        stringBuilder.append(first).append(",");
                    } else {
                        stringBuilder.append(first).append("-").append((i)).append(",");
                    }
                    first = last = 0;
                }
            } else {
                if (first == 0) {
                    first = i;
                } else {
                    last = i;
                }
            }
            SelectValue >>= 1;
            i++;
        }
        if (first != 0) {
            if (last == first || last == 0) {
                stringBuilder.append(first).append(",");
            } else {
                stringBuilder.append(first).append("-").append((i)).append(",");
            }
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        } else {
            return "";
        }
    }

    @Override
    public SelectKey filter(WeChatIO<WCText> wcInfo) {
        Matcher matcher = pattern.matcher(wcInfo.getInfo().getContent());
        if (matcher.find()) {
            SelectKey selectKey = new SelectKey();
            selectKey.isUsed = "占用".equals(matcher.group("isUsed"));
            return (selectKey.day = dayList.indexOf(matcher.group("day").charAt(0))) >= 0 &&
                    buildSet.contains(selectKey.building = matcher.group("building")) ? selectKey : null;
        } else {
            return null;
        }
    }

    private static Integer[] convertBitFlagToStr(int value) {
        List<Integer> l = new ArrayList<>();
        int count = 0;
        for (int i = 0; i <= 14; i++) {
            if ((value & 1) == 1) {
                count++;
            } else if (count > 0) {
                l.add(i * 10 + count);
                count = 0;
            }
            value >>= 1;
        }
        return l.toArray(new Integer[l.size()]);
    }

    @Override
    public void disposeMag(WeChatIO<WCText> weChatIO, SelectKey data) {
//        Calendar
        weChatIO.replyText(String.valueOf(startDay.getTime()));
    }

    class SelectKey {
        int day;
        String building;
        boolean isUsed;
    }

}
