package com.yystudio.base.module.support.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.yystudio.base.constant.SmsConst;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class SmsService {

    public boolean sendVerificationCode(String phoneNumber, String code) {
        // 您的验证码为：123456，5分钟内有效，请勿泄露于他人！
        JSONObject jsonCode = new JSONObject();
        jsonCode.put("code", code);
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phoneNumber)
                .signName(SmsConst.SIGN_NAME)
                .templateCode(SmsConst.TEMPLATE_CODE)
                .templateParam(jsonCode.toJSONString())
                .build();
        SendSmsResponse response = sendSms(sendSmsRequest);
        return response != null && response.getBody().getCode().equals("OK");
    }

    private SendSmsResponse sendSms(SendSmsRequest sendSmsRequest) {

        String accessKeyId = System.getenv("YY_ALIBABA_CLOUD_ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("YY_ALIBABA_CLOUD_ACCESS_KEY_SECRET");
        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and
                // ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                // .securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS
                // token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
//                .region("undefined") // Region ID
                // .httpClient(httpClient) // Use the configured HttpClient, otherwise use the
                // default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                // .serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request
                // parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        // .setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Asynchronously get the return value of the API request
        log.info("调用阿里云发送短信接口参数：{}", JSONObject.toJSONString(sendSmsRequest));
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = null;
        try {
            resp = response.get();
            log.info("调用阿里云发送短信接口返回：{}", JSONObject.toJSONString(resp));

            return resp;
        } catch (InterruptedException | ExecutionException e) {
            log.error("调用阿里云接口发送短信异常", e);
        } finally {
            // Finally, close the client
            client.close();
        }
        return resp;
    }
}
