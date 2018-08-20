package test2;

import org.quartz.*;

import java.util.Date;

public class HelloQuartz2 implements Job {

    // 通过给变量设置getter和setter，可以自动获取jobDetail和trigger中JobDataMap()中的内容
    private String name;
    private Integer never;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNever() {
        return never;
    }

    public void setNever(Integer never) {
        this.never = never;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("job detail message is  " + name + " at " + new Date());

        System.out.println("trigger message is  " + never + " at " + new Date());
    }
}