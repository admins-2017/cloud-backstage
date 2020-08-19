package com.giantlizardcloud.sys.mapper;

import com.giantlizardcloud.config.security.vo.LoginSuccessVo;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface UserDetailsMapper extends BaseMapper<UserDetails> {

    @Select("select sud.user_details_url,sud.user_details_sex,sud.user_details_addr,sud.user_details_mail,\n" +
            "\t\tsud.user_details_tel,ms.shop_name from sys_user_details sud\n" +
            "\t\tLEFT JOIN merchant_shop ms \n" +
            "\t\tON sud.shop_id = ms.shop_id\n" +
            "\t\tWHERE sud.user_id =#{userId}")
    LoginSuccessVo getUserDetailsById(@Param("userId") Long userId);

    @Update("update sys_user set password = #{code} where user_id \n" +
            "= ( select user_id from sys_user_details where user_details_tel = #{number}) ")
    void updateUserPassword(@Param("number") String phoneNumber, @Param("code") String encode);
}
