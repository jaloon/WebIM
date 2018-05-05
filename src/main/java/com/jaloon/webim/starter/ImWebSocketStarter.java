/**
 * Copyright (C), 2017-2018, 厦门普利通信息科技有限公司
 * FileName: ImWebSocketStarter.java
 * Author:   jalon
 * Date:     2018/5/5 13:05
 * Description: IM WebSocket启动器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
package com.jaloon.webim.starter;

import com.jaloon.webim.config.ServerConfig;
import com.jaloon.webim.handler.MessageHandler;
import com.jaloon.webim.listener.IpStatisticListener;
import com.jaloon.webim.listener.ServerAioListener;
import org.tio.server.ServerGroupContext;
import org.tio.websocket.server.WsServerStarter;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈IM WebSocket启动器〉
 *
 * @author jalon
 * @create 2018/5/5 13:05
 * @since 1.0.0
 */
public class ImWebSocketStarter {
    private WsServerStarter wsServerStarter;
    private ServerGroupContext serverGroupContext;

    public ImWebSocketStarter(int port, MessageHandler wsMsgHandler) throws IOException {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);

        serverGroupContext = wsServerStarter.getServerGroupContext();
        serverGroupContext.setName(ServerConfig.PROTOCOL_NAME);
        serverGroupContext.setServerAioListener(ServerAioListener.me);

        //设置ip统计时间段
        serverGroupContext.ipStats.addDurations(ServerConfig.IpStatisticDuration.IP_STATISTIC_DURATIONS);
        //设置ip监控
        serverGroupContext.setIpStatListener(IpStatisticListener.me);
        //设置心跳超时时间
        serverGroupContext.setHeartbeatTimeout(ServerConfig.HEARTBEAT_TIMEOUT);
    }

    public static void start() throws IOException {
        ImWebSocketStarter appStarter = new ImWebSocketStarter(ServerConfig.SERVER_PORT, MessageHandler.me);
        appStarter.wsServerStarter.start();
    }

    public ServerGroupContext getServerGroupContext() {
        return serverGroupContext;
    }

    public WsServerStarter getWsServerStarter() {
        return wsServerStarter;
    }

}