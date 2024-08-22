package com.yystudio.base.config;

import com.yystudio.base.common.constant.StringConst;
import com.yystudio.base.common.util.SmartRequestUtil;
import com.yystudio.base.module.support.repeatsubmit.RepeatSubmitAspect;
import com.yystudio.base.module.support.repeatsubmit.ticket.RepeatSubmitCaffeineTicket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 重复提交配置
 *
 * @Author yy-studio
 * @Date 2021/10/9 18:47
 *
 *
 * @Copyright  远游工作室
 */
@Configuration
public class RepeatSubmitConfig {

    @Bean
    public RepeatSubmitAspect repeatSubmitAspect() {
        RepeatSubmitCaffeineTicket caffeineTicket = new RepeatSubmitCaffeineTicket(this::ticket);
        return new RepeatSubmitAspect(caffeineTicket);
    }

    /**
     * 获取指明某个用户的凭证
     */
    private String ticket(String servletPath) {
        Long userId = SmartRequestUtil.getRequestUserId();
        if (null == userId) {
            return StringConst.EMPTY;
        }
        return servletPath + "_" + userId;
    }
}
