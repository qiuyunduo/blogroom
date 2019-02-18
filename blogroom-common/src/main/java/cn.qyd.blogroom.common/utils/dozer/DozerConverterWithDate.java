package cn.qyd.blogroom.common.utils.dozer;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 转换本地时间工具类
 * @author zzw
 */
public class DozerConverterWithDate extends DozerConverter<LocalDateTime, Date> implements MapperAware {
    public DozerConverterWithDate() {
        super(LocalDateTime.class, Date.class);
    }

    public static Date convertToDate(LocalDateTime source){
        if(source == null) { return null; }
        return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertToLocalDate(Date source){
        if(source == null) { return null; }
        return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public Date convertTo(LocalDateTime source, Date destination) {
        if(source == null) { return null; }
        return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDateTime convertFrom(Date source, LocalDateTime destination) {
        if(source == null) { return null; }
        return LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void setMapper(Mapper mapper) {

    }
}
