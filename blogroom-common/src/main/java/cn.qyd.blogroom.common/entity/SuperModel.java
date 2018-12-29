package cn.qyd.blogroom.common.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author qyd
 * @Date 18-12-29 下午2:28
 **/
@Data
public class SuperModel<T extends Model> extends Model<T> {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime",fill = FieldFill.UPDATE,update = "now()")
    private Date updateTime;

    public static final String ID = "id";

    public static final String CREATETIME = "createTime";

    public static final String UPDATETIME = "updateTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
