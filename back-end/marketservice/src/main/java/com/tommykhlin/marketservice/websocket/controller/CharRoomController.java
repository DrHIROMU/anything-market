package com.tommykhlin.marketservice.websocket.controller;

import com.tommykhlin.marketservice.websocket.model.ChatClientModel;
import com.tommykhlin.marketservice.websocket.model.ServerResponseModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CharRoomController {
    @MessageMapping("/messageControl")
    @SendTo("topic/getResponse")
    public ServerResponseModel said(ChatClientModel responseMessage) throws InterruptedException{
        Thread.sleep(3000);
        return new ServerResponseModel("歡迎來到," + responseMessage.getClientname());
    }
}