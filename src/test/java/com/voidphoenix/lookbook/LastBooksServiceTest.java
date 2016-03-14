package com.voidphoenix.lookbook;

import com.voidphoenix.lookbook.service.LastBooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LookbookApplication.class)
public class LastBooksServiceTest {

    @Autowired
    private LastBooksService lastBooksService;

    @Test
    public void testGettingLastBook(){
        lastBooksService.getLastBookForResouce("asd");
    }

}
