package com.qingspring.demo.utils.QuartzTask;

import com.qingspring.demo.service.ThumbupService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * <h3>qingspring</h3>
 * <p>ThumupTask</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 16:35
 **/
@Slf4j
@Component
public class ThumUpTask extends QuartzJobBean {

    @Autowired
    private ThumbupService thumbupService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // 获取jobDetail存储的参数
//        long taskId = jobDataMap.getLongValue("taskId");
        log.info("LikeTask-------- {}", sdf.format(new Date()));
//        log.info("start-job:---- {}", taskId);

        //将 Redis 里的点赞信息同步到数据库里
        thumbupService.transLikedFromRedis2Mongo();
        thumbupService.transLikedCountFromRedis2Mongo();
    }

}
