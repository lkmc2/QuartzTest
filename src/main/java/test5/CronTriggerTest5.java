package test5;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class CronTriggerTest5 {

    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder
                                .newJob(MyJob.class)
                                .withIdentity("myJob").build();

        // CronTrigger是基于日历的作业调度器，不像SimpleTrigger那样精确指定间隔时间，比SimpleTrigger更常用
        // 比方说指定5月1日执行，或者每周三下午三点执行

        // Cron表达式（在Linux上定时调度使用）
        // 用于配置CronTrigger实例
        // 由7个子表达式组成的字符串，描述了时间表的详细信息，格式：[秒] [分] [小时] [日] [月] [周] [年]
        // * 号表示每，比方说每分、每秒、每小时；?号表示忽略
        CronTrigger trigger = TriggerBuilder.newTrigger()
                                            .withIdentity("myTrigger", "group1")
                                            // 每分每秒每小时每日每月每年都执行
                                            .withSchedule(cronSchedule("* * * * * ? *"))
                                            .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

        // Cron表达式举例（年份可省略）
        // * 号表示每，比方说每分、每秒、每小时；?号表示忽略（不关心）；
        // / 号表示每隔， 5/15 表示从第五秒开始，每隔15秒触发；
        // - 号表示连接，比方说表示周一到周五； # 表示第，#3表示第三周；
        // , 可以同时指定多个值 MON,WED.FRI 表示周一、周三、周五都可以
        // W表示最近的工作日， 15W表示里15号最近的工作日
        // 格式：[秒] [分] [小时] [日] [月] [周] [年]
        // 0  15 10 ? * *   每天10点15分触发
        // 0 0/5 14 * * ?   每天下午的2点到2点59分（整点开始，每隔5分钟触发）
        // 0  15 10 ? * MON-FRI  从周一到周五每天上午的10点15分触发
        // 0  15 10 ? * 6#3   每月的第三周的星期五开始触发（6表示星期五，1是星期日，#3表示第三周）
        // 0  15 10 ? * 6L 2016-2017  从2016年到2017年每月最后一周的星期五的10点15分触发，L表示最后一周
    }

}
