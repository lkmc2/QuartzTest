package test3;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest3 {

    public static void main(String[] args) throws SchedulerException {

        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time is: " + dateFormat.format(date));

        //定义一个JobDetail
        JobDetail job = newJob(HelloQuartz3.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("job1", "group1") //定义name/group
                .build();

        // 获取三秒后的时间
        date.setTime(date.getTime() + 3000);

        // 设置6秒后的时间
        Date endDate = new Date();
        endDate.setTime(date.getTime() + 3000);

        //定义一个Trigger
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .startAt(date)// 设置三秒后启动
                .endAt(endDate) // 设置6秒后结束
                .withSchedule(simpleSchedule() //使用SimpleTrigger
                        .withIntervalInSeconds(2) //每隔2秒执行一次
                        .repeatForever()) //一直执行，奔腾到老不停歇
                .build();

        //创建scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //加入这个调度
        scheduler.scheduleJob(job, trigger);

        //启动之
        scheduler.start();

    }
}


