/**
 * Copyright (C), 2017-2018, 厦门普利通信息科技有限公司
 * FileName: IpStatisticListener.java
 * Author:   jalon
 * Date:     2018/5/5 12:50
 * Description: IP统计监听器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
package com.jaloon.webim.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.intf.Packet;
import org.tio.core.stat.IpStat;
import org.tio.core.stat.IpStatListener;
import org.tio.utils.json.Json;

/**
 * 〈一句话功能简述〉<br>
 * 〈IP统计监听器〉
 *
 * @author jalon
 * @create 2018/5/5 12:50
 * @since 1.0.0
 */
public class IpStatisticListener implements IpStatListener {
    private static Logger logger = LoggerFactory.getLogger(IpStatisticListener.class);
    public static final IpStatisticListener me = new IpStatisticListener();

    private IpStatisticListener() {}

    @Override
    public void onExpired(GroupContext groupContext, IpStat ipStat) {
        // 在这里把统计数据入库中或日志
        if (logger.isInfoEnabled()) {
            logger.info("可以把统计数据入库\r\n{}", Json.toFormatedJson(ipStat));
        }
    }

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect, IpStat ipStat) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("onAfterConnected\r\n{}", Json.toFormatedJson(ipStat));
        }
    }

    @Override
    public void onDecodeError(ChannelContext channelContext, IpStat ipStat) {
        if (logger.isInfoEnabled()) {
            logger.info("onDecodeError\r\n{}", Json.toFormatedJson(ipStat));
        }
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess, IpStat ipStat) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("onAfterSent\r\n{}\r\n{}", packet.logstr(), Json.toFormatedJson(ipStat));
        }
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize, IpStat ipStat) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), Json.toFormatedJson(ipStat));
        }
    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes, IpStat ipStat) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("onAfterReceivedBytes\r\n{}", Json.toFormatedJson(ipStat));
        }
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, IpStat ipStat, long cost) throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), Json.toFormatedJson(ipStat));
        }
    }
}