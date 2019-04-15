package com.my.myshop.sms;

import com.alibaba.fastjson.JSON;
import com.my.myshop.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("verCodeService")
@Slf4j
public class MiaoDiSmsSender implements SmsSender{


    private static String operation = "/industrySMS/sendSMS";

    @Override
    public  void sendSms(String phone,String tplId,String params){
       /* try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = sdf.format(new Date());
            String sig = MD5Util.getMD5(Constants.MDSMS_ACCOUNT_SID +Constants.MDSMS_AUTH_TOKEN +timestamp);
//            String url = Constants.MDSMS_REST_URL +operation;
            String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
            Map<String,String> param = new HashMap<>();
            param.put("accountSid",Constants.MDSMS_ACCOUNT_SID);
            param.put("to",phone);
//            param.put("templateid",tplId);
            param.put("smsContent","【秒嘀科技】您的验证码是345678，30分钟输入有效");
//            param.put("param",params);
            param.put("timestamp",timestamp);
            param.put("sig",sig);
            param.put("respDataType","json");
            String result = HttpUtil.post(url,param);
            JSONObject jsonObject = JSON.parseObject(result);
            if(!jsonObject.getString("respCode").equals("00000")){
                log.error("fail to send sms to "+phone+":"+params+":"+result);
            }
        } catch (Exception e) {
            log.error("fail to send sms to "+phone+":"+params);
        }*/
        Map<String, String> pp = new HashMap<>();
        String url = "http://web.duanxinwang.cc/asmx/smsservice.aspx";
        String name = "dxw15656709003";
        String pwd = "9FA6EB4D075A98F84F896CF0D451";
        String sign = "会民优购";
        String type = "pt";
        pp.put("mobile","13866544746");
        pp.put("content","11111111111111");
        pp.put("name", name);
        pp.put("pwd", pwd);
        pp.put("sign", sign);
        pp.put("type", type);
//        String s = HttpUtil.get(url, pp);
        log.error(JSON.toJSONString(pp));
    }

}
