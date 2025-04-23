package com.yyt.examtreasure.constant;


/**
 * Redis 常量
 */
public interface RedisConstant {


    /**
     * 用户签到记录的Redis key前缀
     */
    String USER_SIGN_IN_REDIS_KEY_PREFIX = "user:signins";

    //Interface abstract methods cannot have body要加static
    /**
     * h获取用户签到记录的Redis Key
     * @param year 年份
     * @param userId 用户id
     * @return 拼接好的Redis Key
     */
    static String getUserSignInRedisKey(int year,long userId){
        return String.format("%s:%s:%s",USER_SIGN_IN_REDIS_KEY_PREFIX,year,userId);
    }

}
