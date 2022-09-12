package com.security.common;

import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerminateBean {

    @PreDestroy
    public void onDestroy() throws Exception {
        log.info("Spring Container is destroyed!");
    }	
	
}
