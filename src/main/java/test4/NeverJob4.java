package test4;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NeverJob4 implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateFormat.format(new Date());
        System.out.println("I am never, now is " + dateStr);
    }
}