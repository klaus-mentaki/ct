package producer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductLog {
    private String startTime = "2018-01-01 00:00:00";
    private String endTime = "2018-12-01 00:00:00";

    //生产数据
    //用于存放随机的电话号码
    private Map<String, String> phoneNameMap = new HashMap<>();
    private List<String> phoneList = new ArrayList<>();

    public void initPhone() {
        phoneList.add("15369468720");
        phoneList.add("19920860202");
        phoneList.add("18411925860");
        phoneList.add("14473548449");
        phoneList.add("18749966182");
        phoneList.add("19379884788");
        phoneList.add("19335715448");
        phoneList.add("18503558939");
        phoneList.add("13407209608");
        phoneList.add("15596505995");
        phoneList.add("17519874292");
        phoneList.add("15178485516");
        phoneList.add("19877232369");
        phoneList.add("18706287692");
        phoneList.add("18944239644");
        phoneList.add("17325302007");
        phoneList.add("18839074540");
        phoneList.add("19879419704");
        phoneList.add("16480981069");
        phoneList.add("18674257265");
        phoneList.add("18302820904");
        phoneList.add("15133295266");
        phoneList.add("17868457605");
        phoneList.add("15490732767");
        phoneList.add("15064972307");

        phoneNameMap.put("15369468720", "李雁");
        phoneNameMap.put("19920860202", "卫艺");
        phoneNameMap.put("18411925860", "仰莉");
        phoneNameMap.put("14473548449", "陶欣悦");
        phoneNameMap.put("18749966182", "施梅梅");
        phoneNameMap.put("19379884788", "金虹霖");
        phoneNameMap.put("19335715448", "魏明艳");
        phoneNameMap.put("18503558939", "华贞");
        phoneNameMap.put("13407209608", "华啟倩");
        phoneNameMap.put("15596505995", "仲采绿");
        phoneNameMap.put("17519874292", "卫丹");
        phoneNameMap.put("15178485516", "戚丽红");
        phoneNameMap.put("19877232369", "何翠柔");
        phoneNameMap.put("18706287692", "钱溶艳");
        phoneNameMap.put("18944239644", "钱琳");
        phoneNameMap.put("17325302007", "缪静欣");
        phoneNameMap.put("18839074540", "焦秋菊");
        phoneNameMap.put("19879419704", "吕访琴");
        phoneNameMap.put("16480981069", "沈丹");
        phoneNameMap.put("18674257265", "褚美丽");
        phoneNameMap.put("18302820904", "孙怡");
        phoneNameMap.put("15133295266", "许婵");
        phoneNameMap.put("17868457605", "曹红恋");
        phoneNameMap.put("15490732767", "吕柔");
        phoneNameMap.put("15064972307", "冯怜云");
    }

    /**
     * 生产数据
     * 形式：15064972307，17868457605，2019-09-28 13:28:29 0223
     */
    public void product() {
        String callerNum;
        String calleeNum;

        String callerName;
        String calleeName;

        //取主叫电话号码
        int callerIndex = (int) (Math.random() * phoneList.size());
        callerNum = phoneList.get(callerIndex);
        callerName = phoneNameMap.get(callerNum);

        //取被叫电话号码
        while (true) {
            int calleeIndex = (int) (Math.random() * phoneList.size());
            calleeNum = phoneList.get(callerIndex);
            calleeName = phoneNameMap.get(calleeNum);
            if (calleeNum.equals(callerNum)) break;
        }

        String buildTime = randomBuildTime(startTime, endTime);
        DecimalFormat decimalFormat = new DecimalFormat();
        String duration = decimalFormat.format((int) 30 * 60 * Math.random());

        System.out.println(callerNum+","+callerName+","+calleeNum+","+calleeName+","+buildTime+duration);

    }

    /**
     * 根据传入时间区间，随机建立通话时间, 时间格式：yyyy-MM-dd HH:MM:SS
     * startTimeTS + （endTimeTS - startTimeTs) * Math.random();
     */
    public String randomBuildTime(String startTime, String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");

        try {
            Date startDate = simpleDateFormat.parse(startTime);
            Date endDate = simpleDateFormat.parse(endTime);

            if (endDate.getTime() < startDate.getTime()) return null;

            long randomTS = startDate.getTime() + endDate.getTime() - startDate.getTime();
            Date resultDate = new Date(randomTS);
            String resultDateString = simpleDateFormat.format(resultDate);
            return resultDateString;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数据写入到文件流
     */
    public void writeLog() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(""), Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ProductLog productLog = new ProductLog();
        productLog.initPhone();
        while (true){
            Thread.sleep(300);
            productLog.product();
        }
    }
}
