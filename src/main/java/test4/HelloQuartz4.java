package test4;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloQuartz4 implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(new Date());
        System.out.println("now is " + dateStr);
    }
}