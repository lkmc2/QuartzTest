package test4;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest4 {

    // 使用simpleSchedule的例子
    public static void main(String[] args) throws SchedulerException {

        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time is: " + dateFormat.format(date));

        //定义一个JobDetail
        JobDetail job = newJob(HelloQuartz4.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("job1", "group1") //定义name/group
                .build();

        // 获取4秒后的时间
        date.setTime(date.getTime() + 4000);

        // 获取4 + 6后的时间
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);

        //(距离当前时间4秒后执行且仅执行一次任务)定义一个Trigger
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1") //定义name/group
                .startAt(date)
                .build();

        //定义一个JobDetail
        JobDetail job2 = newJob(ShowJob4.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("show1", "group1") //定义name/group
                .build();

        //(距离当前时间4秒后首次执行，之后每隔2秒执行一次任务，重复5次
        SimpleTrigger trigger2 = (SimpleTrigger) newTrigger()
                .withIdentity("trigger2", "group1") //定义name/group
                .startAt(date)
                .withSchedule(
                        simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(5)
                ).build();

        //定义一个JobDetail
        JobDetail job3 = newJob(NeverJob4.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("never3", "group1") //定义name/group
                .build();

        //(距离当前时间4秒后首次执行，之后每隔2秒执行一次任务，重复5次
        SimpleTrigger trigger3 = (SimpleTrigger) newTrigger()
                .withIdentity("trigger3", "group1") //定义name/group
                .startAt(date) // 开始时间
                .endAt(endDate) // 结束时间（结束时间优先于withRepeatCount，时间结束则不再重复）
                .withSchedule(
                        simpleSchedule()
                                .withIntervalInSeconds(2)
                                .withRepeatCount(5)
                ).build();

        //创建scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //加入这个调度（可以设置多次调度器）
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);
        scheduler.scheduleJob(job3, trigger3);


        //启动之
        scheduler.start();

    }
}


