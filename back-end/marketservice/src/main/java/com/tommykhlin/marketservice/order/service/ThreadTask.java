package com.tommykhlin.marketservice.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Slf4j
public class ThreadTask implements Runnable{
    private String msg;

    public ThreadTask(String msg){
        this.msg = msg;
    }

    @Override
    public void run() {
        try{
            System.out.println(this.msg);
            Thread.sleep(3000);
        }catch (InterruptedException ex){
            log.error(ex.getMessage());
        }
    }
}
