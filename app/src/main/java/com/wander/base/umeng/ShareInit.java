package com.wander.base.umeng;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by wander on 2016/3/31.
 * email 805677461@qq.com
 */
public class ShareInit {
    public static void init(){
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx967daebe835fbeac","5bb696d9ccd75a38c8a0bfe0675559b3");
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("100424468","c7394704798a158208a74ab60104f0ba");
    }
}
