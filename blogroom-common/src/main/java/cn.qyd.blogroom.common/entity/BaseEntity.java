package cn.qyd.blogroom.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 18-12-29 下午2:28
 **/

@MappedSuperclass
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1020633525801516817L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "created_user_id", updatable = false,columnDefinition = "DATETIME COMMENT '创建者'")
    private LocalDateTime createdUserId;

    @Getter @Setter
    @Column(name = "created_date", updatable = false,columnDefinition = "DATETIME COMMENT '创建时间'")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Getter @Setter
    @Column(name = "update_user_id", updatable = false,columnDefinition = "DATETIME COMMENT '操作者'")
    private LocalDateTime updateUserId;

    @Getter @Setter
    @Column(name = "update_date", updatable = false,columnDefinition = "DATETIME COMMENT '操作时间'")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateDate;
}
