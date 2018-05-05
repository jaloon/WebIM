/**
 * Copyright (C), 2017-2018, 厦门普利通信息科技有限公司
 * FileName: ServerAioListener.java
 * Author:   jalon
 * Date:     2018/5/5 13:00
 * Description: 服务器I/O监听器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
package com.jaloon.webim.listener;

import com.jaloon.webim.constant.ServerConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.websocket.server.WsServerAioListener;

/**
 * 〈一句话功能简述〉<br> 
 * 〈服务器I/O监听器〉
 *
 * @author jalon
 * @create 2018/5/5 13:00
 * @since 1.0.0
 */
public class ServerAioListener extends WsServerAioListener {
    private static Logger logger = LoggerFactory.getLogger(ServerAioListener.class);
    public static final ServerAioListener me = new ServerAioListener();

    private ServerAioListener() {}

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
        super.onAfterConnected(channelContext, isConnected, isReconnect);
        if (logger.isInfoEnabled()) {
            logger.info("onAfterConnected\r\n{}", channelContext);
        }

        //绑定到群组，后面会有群发
        Aio.bindGroup(channelContext, ServerConst.GROUP_ID);
    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
        super.onAfterSent(channelContext, packet, isSentSuccess);
        if (logger.isInfoEnabled()) {
            logger.info("onAfterSent\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
        super.onBeforeClose(channelContext, throwable, remark, isRemove);
        if (logger.isInfoEnabled()) {
            logger.info("onBeforeClose\r\n{}", channelContext);
        }
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
        super.onAfterDecoded(channelContext, packet, packetSize);
        if (logger.isInfoEnabled()) {
            logger.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
        super.onAfterReceivedBytes(channelContext, receivedBytes);
        if (logger.isInfoEnabled()) {
            logger.info("onAfterReceivedBytes\r\n{}", channelContext);
        }
    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
        super.onAfterHandled(channelContext, packet, cost);
        if (logger.isInfoEnabled()) {
            logger.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }
}