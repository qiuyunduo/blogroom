package cn.qyd.blogroom.user.utils;

import cn.qyd.blogroom.common.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @Author qyd
 * @Date 19-4-4 下午2:50
 **/
@Component
public class StorageValidateCode {
    @Autowired(required = false)
    private ValueOperations<String,Object> valueOperations;

    public void setResetPwdValidateCode(String email, String code) {
        valueOperations.set(Constant.PREFIX_RESET_PASSWORD_EMAIL_VALIDATE_CODE+email,code,60,TimeUnit.SECONDS);
    }

    public Boolean checkResetPwdValidateCode(String email,String code) {
        Object o = valueOperations.get(Constant.PREFIX_RESET_PASSWORD_EMAIL_VALIDATE_CODE + email);
        if(o == null) {
            return false;
        }
        return String.valueOf(o).equals(code);
    }

    public void setRegisterValidateCode(String email, String code) {
        valueOperations.set(Constant.PREFIX_REGISTER_EMAIL_VALIDATE_CODE+email,code,60,TimeUnit.SECONDS);
    }

    public Boolean checkRegisterValidateCode(String email, String code) {
        Object o = valueOperations.get(Constant.PREFIX_REGISTER_EMAIL_VALIDATE_CODE + email);
        if(o == null) {
            return false;
        }
        return String.valueOf(o).equals(code);
    }
}
