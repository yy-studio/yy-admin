package com.yystudio.base.constant;

/**
 * redis key 常量类
 *
 * @Author yy-studio
 * @Date 2022-05-30 21:22:12
 *
 *
 * @Copyright  远游工作室
 */
public class RedisKeyConst {

    public static final String SEPARATOR = ":";

    public static class Support {

        public static final String FILE_PRIVATE_VO = "file:private:";

        public static final String SERIAL_NUMBER_LAST_INFO = "serial-number:last-info";

        public static final String SERIAL_NUMBER = "serial-number:";

        public static final String CAPTCHA = "captcha:";

        public static final String SMS = "sms:";

        public static final String SMS_REGISTER = "sms-register:";
        public static final String EMAIL_REGISTER = "email-register:";

        public static final String SMS_LOGIN = "sms-login:";

        public static final String SMS_BIND = "sms-bind:";

    }
}
