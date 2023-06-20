package com.xianju.demo.Handler;

import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Slf4j
@Component
public class SessionInformationExpiredHandler implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {

        ServletUtils.render(sessionInformationExpiredEvent.getRequest(),
                sessionInformationExpiredEvent.getResponse(), Result.fail(400,"多用户同时登陆"));
    }
}
