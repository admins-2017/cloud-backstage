package com.giantlizardcloud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.giantlizardcloud.config.json.JsonUtils;
import com.giantlizardcloud.merchant.dto.QueryOrderByConditionDto;
import com.giantlizardcloud.merchant.entity.Classification;
import com.giantlizardcloud.merchant.entity.Commodity;
import com.giantlizardcloud.merchant.mapper.OrderMapper;
import com.giantlizardcloud.merchant.service.IClassificationService;
import com.giantlizardcloud.merchant.service.ICommodityService;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.giantlizardcloud.merchant.vo.InventoryAndCommodityVo;
import com.giantlizardcloud.merchant.vo.OrderAndClientAndUserVO;
import com.giantlizardcloud.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CloudBackstageApplicationTests {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Test
//    void contextLoads() {
//        String url = "com.giantlizardcloud.config.quartz.jobs.TestJob";
//        try {
//            Class<?> clazz = Class.forName(url);
//            //获取本类的所有方法，存放入数组
//            Method[] methods = clazz.getDeclaredMethods();
//            for (Method method : methods) {
//                System.out.println("方法名："+method.getName());
//                //获取本方法所有参数类型，存入数组
//                Class<?>[] getTypeParameters = method.getParameterTypes();
//                if(getTypeParameters.length==0){
//                    System.out.println("此方法无参数");
//                }
//                for (Class<?> class1 : getTypeParameters) {
//                    String parameterName = class1.getName();
//                    System.out.println("参数类型："+parameterName);
//                }
//                System.out.println("****************************");
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testList(){
//        List<User> names = new ArrayList<>();
//        User user = new User();
//        user.setUsername("aaa");
//        user.setPassword("aaa");
//        names.add(user);
//        User user2 = new User();
//        user2.setUsername("aaa");
//        user2.setPassword("aaa");
//        names.add(user2);
//        User user3 = new User();
//        user3.setUsername("aaa");
//        user3.setPassword("aaa");
//        names.add(user3);
//        String s = JsonUtils.objectToJson(names);
//        System.out.println(s);
//    }
//
//    @Test
//    public void testGetCommodityByClassificationId(){
//        QueryOrderByConditionDto dto = new QueryOrderByConditionDto();
//        dto.setPage(0);
//        dto.setSize(10);
//        dto.setOrderInsertStartTime("2020-10-20");
//        dto.setOrderInsertEndTime("2020-10-23");
//        dto.setOrderUnpaidAmount(1.00);
//        List<OrderAndClientAndUserVO> list = orderMapper.getPageByCondition(dto);
//        list.forEach(System.out::println);
//    }
}
