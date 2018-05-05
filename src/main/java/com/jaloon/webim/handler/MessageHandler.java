/**
 * Copyright (C), 2017-2018, 厦门普利通信息科技有限公司
 * FileName: MessageHandler.java
 * Author:   jalon
 * Date:     2018/5/5 11:52
 * Description: 消息处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
package com.jaloon.webim.handler;

import com.jaloon.webim.config.ServerConfig;
import com.jaloon.webim.constant.ServerConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Objects;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息处理〉
 *
 * @author jalon
 * @create 2018/5/5 11:52
 * @since 1.0.0
 */
public class MessageHandler implements IWsMsgHandler {
    private static Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    public static final MessageHandler me = new MessageHandler();

    private MessageHandler() {}

    /**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     */
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext)
            throws Exception {
        String clientIp = httpRequest.getClientIp();
        logger.info("收到来自{}的ws握手包\r\n{}", clientIp, httpRequest.toString());
        return httpResponse;
    }

    /**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    /**
     * 当客户端发close flag时，会走这个方法
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        Aio.remove(channelContext, "receive close flag");
        return null;
    }

    /**
     * 字符消息（binaryType = blob）过来后会走这个方法
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequestPacket();//获取websocket握手包
        if (logger.isDebugEnabled()) {
            logger.debug("握手包:{}", httpRequest);
        }

        logger.info("收到ws消息:{}", text);

        if (Objects.equals("心跳内容", text)) {
            return null;
        }

        String msg = channelContext.getClientNode().toString() + " 说：" + text;
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
        WsResponse wsResponse = WsResponse.fromText(msg, ServerConfig.CHARSET);
        //群发
        Aio.sendToGroup(channelContext.getGroupContext(), ServerConst.GROUP_ID, wsResponse);

        //返回值是要发送给客户端的内容，一般都是返回null
        return null;
    }
}