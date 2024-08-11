package com.mycompany.p2pTradeSpringProject.component;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A component that checks the current debug mode status.
 */
@Getter
@Component
public class Debug {

    private static final Logger logger = LoggerFactory.getLogger(Debug.class);

    @Value("${debug.mode:false}")
    private boolean debugMode;

}
