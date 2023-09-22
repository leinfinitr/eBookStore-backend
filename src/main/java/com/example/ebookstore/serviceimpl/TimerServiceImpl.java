package com.example.ebookstore.serviceimpl;

import com.example.ebookstore.service.TimerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class TimerServiceImpl implements TimerService {
    // 计时器
    private long startTime;

    @Override
    public void startTime(String userName) {
        startTime = System.currentTimeMillis();
        System.out.println(userName + " " + this);
        System.out.println(userName + " 登陆时间：" + startTime + "ms");
    }

    @Override
    public long endTime(String userName) {
        System.out.println(userName + " " + this);
        System.out.println(userName + " 当前时间：" + System.currentTimeMillis() + "ms");
        System.out.println(userName + " 登陆时间：" + startTime + "ms");
        System.out.println(userName + " 登录耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        return System.currentTimeMillis() - startTime;
    }
}
