package com.my.myshop.common.constants;

/**
 * Created by JackWangon[www.] 2017/7/29.
 */
public class Constants {

    public static final String  X_AUTH_TOKEN = "x-auth-token";
    /**用户token**/
    public static final String REQUEST_TOKEN_KEY = "user-token";
    /**客户端版本**/
    public static final String REQUEST_VERSION_KEY = "version";
    /**客户端平台 android/ios**/
    public static final String REQUEST_PLATFORM_KEY = "platform";

    public static final String REQUEST_TYPE_KEY = "type";
    /**自定义状态码 start**/
    public static final int RESP_STATUS_OK = 200;

    public static final int RESP_STATUS_NOAUTH = 401;

    public static final int RESP_STATUS_INTERNAL_ERROR = 500;

    public static final int RESP_STATUS_BADREQUEST = 400;

    public static final int PARAM_VALID_ERROR = 4001;//参数验证错误

    public static final int NOT_LOGIN = 4002;//未登录
    /**自定义状态码 end**/

    /**秒滴SMS start**/
    //account_sid
    public static final String MDSMS_ACCOUNT_SID = "a18bae4925884d34b0bba41a40d19388";
    //auth_token
    public static final String MDSMS_AUTH_TOKEN = "16403819e0944daf9a9e4944a1fa2568";
    //API接口
    public static final String MDSMS_REST_URL = "https://api.miaodiyun.com/20150822";
    //模板ID
    public static final String MDSMS_VERCODE_TPLID = "1519329784";

    /**秒滴SMS end**/


    /***七牛keys start****/
    public static final String QINIU_ACCESS_KEY="";

    public static final String QINIU_SECRET_KEY="";

    public static final String QINIU_HEAD_IMG_BUCKET_NAME="";

    public static final String QINIU_HEAD_IMG_BUCKET_URL="";

    /***七牛keys end****/

    /**百度云推送 start**/
    public static final String BAIDU_YUN_PUSH_API_KEY="";

    public static final String BAIDU_YUN_PUSH_SECRET_KEY="";

    public static final String CHANNEL_REST_URL = "api.push.baidu.com";
    /**百度云推送end**/



}
