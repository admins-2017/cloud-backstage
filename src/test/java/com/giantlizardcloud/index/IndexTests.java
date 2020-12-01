package com.giantlizardcloud.index;

import com.giantlizardcloud.config.redis.RedisOperator;
import com.giantlizardcloud.merchant.enums.IndexKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@Slf4j
public class IndexTests {

    @Autowired
    private RedisOperator operator;

    @Test
    public void testZSet(){
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"雀巢咖啡",3.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"康师傅冰糖雪梨",4.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"康师傅酸梅汁",2.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"茶π",1.0);
        operator.addZset(IndexKeyEnum.SALE.getMessage(),"阿萨姆奶茶",7.0);
    }

    @Test
    public void testRankZSet(){
        Set<ZSetOperations.TypedTuple<String>> testZSet = operator.reverseRankZset(IndexKeyEnum.SALE.getMessage(), 0, -1);
        testZSet.forEach( a-> {
            System.out.println(a.getValue() + ": " +a.getScore());
        });
    }

    @Test
    public void testHash(){
        LocalDate now = LocalDate.now();
        System.out.println();
        System.out.println(now.getMonthValue());
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"purchase",0);
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"sale",0);
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"salesAmount",0);
        operator.hset(now.getYear()+"-"+now.getMonthValue(),"amountOfPayout",0);

    }

    @Test
    public void getAllObject(){
        LocalDate now = LocalDate.now();
        Map<Object, Object> hgetall = operator.hgetall(now.getYear() + "-" + now.getMonthValue());
        for(Object s:hgetall.keySet()){
                         System.out.println("key : "+s+" value : "+hgetall.get(s));
                   }
    }

    @Test
    public void testObject(){
        LocalDate now = LocalDate.now();
         operator.hashIncrBy(now.getYear() + "-" + now.getMonthValue(),"purchase",-1);
    }
}
