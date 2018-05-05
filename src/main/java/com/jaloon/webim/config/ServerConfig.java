/**
 * Copyright (C), 2017-2018, 厦门普利通信息科技有限公司
 * FileName: ServerConfig.java
 * Author:   jalon
 * Date:     2018/5/5 11:43
 * Description: 服务器配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
package com.jaloon.webim.config;

import org.tio.utils.time.Time;

/**
 * 〈一句话功能简述〉<br> 
 * 〈服务器配置〉
 *
 * @author jalon
 * @create 2018/5/5 11:43
 * @since 1.0.0
 */
public class ServerConfig {
    /**
     * 协议名字(可以随便取，主要用于开发人员辨识)
     */
    public static final String PROTOCOL_NAME = "im";

    public static final String CHARSET = "utf-8";
    /**
     * 监听的ip
     */
    public static final String SERVER_IP = null;//null表示监听所有，并不指定ip

    /**
     * 监听端口
     */
    public static final int SERVER_PORT = 9326;

    /**
     * 心跳超时时间，单位：毫秒
     */
    public static final int HEARTBEAT_TIMEOUT = 1000 * 60;

    /**
     * ip数据监控统计，时间段
     * @author jalon
     *
     */
    public static interface IpStatisticDuration {
        Long DURATION_1 = Time.MINUTE_1 * 5;
        Long[] IP_STATISTIC_DURATIONS = new Long[] { DURATION_1 };
    }
}