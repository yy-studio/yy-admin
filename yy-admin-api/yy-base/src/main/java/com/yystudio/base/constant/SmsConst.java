package com.yystudio.base.constant;

public class SmsConst {

    public static final String ACCESS_KEY_ID = System.getenv("YY_ALIBABA_CLOUD_ACCESS_KEY_ID");
    public static final String ACCESS_KEY_SECRET = System.getenv("YY_ALIBABA_CLOUD_ACCESS_KEY_SECRET");
    // 临时签名，上线后申请正式的
    public static final String SIGN_NAME = "天津东丽区远游软件工作室";
    // 临时模板，上线后申请正式的
    public static final String TEMPLATE_CODE = "SMS_302657532";
}
