package test3;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloQuartz3 implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(new Date());
        System.out.println("now is " + dateStr);

        Trigger trigger = context.getTrigger();
        System.out.println("Start Time is " + trigger.getStartTime());
        System.out.println("End Time is " + trigger.getEndTime());

        JobKey jobKey = trigger.getJobKey();
        System.out.println("JobKey info jobName:" + jobKey.getName() + ",jobGroup:" + jobKey.getGroup());
    }
}