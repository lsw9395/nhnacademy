package com.nhnacademy.springbootaccount.actuator;

import io.micrometer.core.lang.Nullable;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Endpoint(id="counter")
public class CounterEndpoint {
    private final AtomicLong counter = new AtomicLong();
    @ReadOperation
    public long count(){
        return counter.get();
    }


    @WriteOperation
    public Long increment(@Nullable Long delta){
        if(delta == null){
            return counter.incrementAndGet();
        }
        return counter.addAndGet(delta);
    }

    @DeleteOperation
    public long reset() {
        counter.set(0);
        return counter.get();
    }
}
