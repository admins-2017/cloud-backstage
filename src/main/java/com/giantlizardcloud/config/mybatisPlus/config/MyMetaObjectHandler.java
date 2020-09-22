package com.giantlizardcloud.config.mybatisPlus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * MyMetaObjectHandler 自动填充处理器 实现MP提供的 MetaObjectHandler接口
 *
 * @author kangdongwei
 * @date 2019.11.18
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 该方法默认是所有类的添加方法都执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        /*
         * fieldName:写实体类中的属性名
         * fieldVal:需要填充的值
         */
        //该方法判断当前对象中是否含有insertTime属性，有就自动填充 没有就不会自动填充
        boolean hasSetter =metaObject.hasSetter("createTime");
        if (hasSetter){
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }
        boolean hasSetter2 =metaObject.hasSetter("record_time");
        if (hasSetter2){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.strictInsertFill(metaObject, "recordTime", LocalDateTime.class, simpleDateFormat.format(LocalDateTime.now()));
        }
        if (metaObject.hasSetter("insertTime")){
            this.strictInsertFill(metaObject, "insertTime", LocalDateTime.class, LocalDateTime.now());
        }
        if (metaObject.hasSetter("insertUser")){
            this.strictInsertFill(metaObject, "insertUser", Long.class,  SecurityUntil.getUserId());
        }
        if (metaObject.hasSetter("creatorId")){
            this.strictInsertFill(metaObject, "creatorId", Long.class,  SecurityUntil.getUserId());
        }
        if (metaObject.hasSetter("creatorName")){
            this.strictInsertFill(metaObject, "creatorName", String.class,  SecurityUntil.getUserName());
        }
        if (metaObject.hasSetter("createdTime")){
            this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, LocalDateTime.now());
        }
    }

    /**
     * 该方法默认是所有类的更新方法都执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //该方法判断对象的对应属性是否赋值，如果没赋值就自动填充
        Object val=getFieldValByName("updateTime",metaObject);
        if (null==val){
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        }
        Object val2=getFieldValByName("updateUser",metaObject);
        if (null==val2){
            Long updateUser= SecurityUntil.getUserId();
            this.strictUpdateFill(metaObject,"updateUser",Long.class,updateUser);
        }
        Object val3=getFieldValByName("updatedTime",metaObject);
        if (null==val3){
            Long updateUser= SecurityUntil.getUserId();
            this.strictUpdateFill(metaObject,"updatedTime",LocalDateTime.class,LocalDateTime.now());
        }
    }
}
