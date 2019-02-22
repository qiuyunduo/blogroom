package cn.qyd.blogroom.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 18-12-29 下午2:28
 **/

@MappedSuperclass
@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1020633525801516817L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "add_time", updatable = false,columnDefinition = "DATETIME COMMENT '创建时间'")
    private LocalDateTime addTime = LocalDateTime.now();

    @Column(name = "update_time",columnDefinition = "DATETIME COMMENT '最新更新时间'")
    private LocalDateTime updateTime;
}
