package test1;

import org.quartz.*;

import java.util.Date;

public class HelloQuartz implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("jobName = " + jobKey.getName() + ", jobGroup = " + jobKey.getGroup());

        TriggerKey triggerKey = context.getTrigger().getKey();
        System.out.println("triggerName = " + triggerKey.getName() + ", triggerGroup = " + triggerKey.getGroup());

        JobDetail detail = context.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        System.out.println("job detail message is  " + name + " at " + new Date());

        Trigger trigger = context.getTrigger();
        float never = trigger.getJobDataMap().getInt("never");
        System.out.println("trigger message is  " + never + " at " + new Date());

        // 获取将jobDetail和Trigger中JobDataMap()合并后的JobDataMap()，
        // 如有重复，trigger中的key会覆盖掉jobDetail中的key
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        String name1 = mergedJobDataMap.getString("name");
        int never1 = mergedJobDataMap.getInt("never");

        System.out.println("mergedJobDataMap message is " + name1 + ", " + never1);
    }
}