package com.example.demo.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * 简单websocket demo
 *
 * @author hzx
 */
@Slf4j
@ServerEndpoint(value = "/websocketTest/{userId}")
public class TestWebSocketController {

    private  String userId;

    @OnOpen
    @SneakyThrows
    public void onOpen(@PathParam("userId") String userId, Session session){
        this.userId = userId;
        log.info("新连接：{}",userId);
    }

    /**
     *   关闭时执行
     */
    @OnClose
    public void onClose(){
        log.debug("连接：{} 关闭",this.userId);
    }

    /**
     * //收到消息时执行
     * @param message
     * @param session
     */
    @OnMessage
    @SneakyThrows
    public void onMessage(String message, Session session) {
        log.debug("收到用户{}的消息{}",this.userId,message);
        //回复用户
        session.getBasicRemote().sendText("收到 "+this.userId+" 的消息 ");
    }

    /**
     * 连接错误时执行
     */

    @OnError
    @SneakyThrows
    public void onError(Session session, Throwable error){
        log.debug("用户id为：{}的连接发送错误",this.userId);
        error.printStackTrace();
    }







}
