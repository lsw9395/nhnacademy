package springframework;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.edu.springframework.DoorayHookSender;
import com.nhnacademy.edu.springframework.DoorayMessageSender;
import com.nhnacademy.edu.springframework.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class DoorayMessageSenderMockTest {
    @InjectMocks
    private DoorayMessageSender doorayMessageSender;

    @Mock
    private DoorayHookSender doorayHookSender;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testDoorayMessage() {
        when(doorayHookSender.send(new DoorayHook())).thenReturn(false);
        User user = new User("이성운","dltjddns93@gmail.com","01098955684");
        String message = "test";
        boolean acutal = doorayMessageSender.sendMessage(user,message);
        Assertions.assertTrue(acutal);
    }
}
