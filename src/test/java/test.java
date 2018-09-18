import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class test {

    public static void main(String [] args){
        String test = "查询今天天佑楼占用课室";
        String builds[] = {"知行", "明德", "弘毅", "天佑", "弘毅", "艺悦", "精工"};
        StringJoiner asd = new StringJoiner(")|(", "查询(?<day>[前|昨|今|明|后])天(?<building>(全部)|(", "))楼(?<isUsed>(占用)|(空闲))课室");
        for (String str : builds) {
            asd.add(str);
        }
        String match = "查询(?<day>[\\u4E00-\\u9FA5])天(?<building>[\\u4E00-\\u9FA5]{2})楼(?<isUsed>(占用)|(空闲))课室";
        Pattern pattern = Pattern.compile("查询(?<day>[\\u4E00-\\u9FA5])天(?<building>[\\u4E00-\\u9FA5]{2})楼(?<isUsed>(占用)|(空闲))课室");
        Matcher matcher = pattern.matcher(test);
        while (matcher.find()) {
            System.out.println(matcher.group("day"));
            System.out.println(matcher.group("building"));
            System.out.println(matcher.group("isUsed"));
        }
    }

    public abstract void asd(String aasda);
}
