package cn.qyd.blogroom.common.utils.dozer;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

import java.time.LocalDateTime;

/**
 * Dozer中LocalDateTime转LocalDateTime出现问题,暂时这样解决
 * @author qyd
 */
public class DozerConverterWithLocalDateTime extends DozerConverter<LocalDateTime, LocalDateTime> implements MapperAware {
    public DozerConverterWithLocalDateTime() {
        super(LocalDateTime.class, LocalDateTime.class);
    }

    @Override
    public void setMapper(Mapper mapper) {

    }

    @Override
    public LocalDateTime convertTo(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
        if(localDateTime2 == null) { return null; }
        return localDateTime2;
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
        if(localDateTime == null) { return null; }
        return localDateTime;
    }
}
