package cn.qyd.blogroom.common.utils.dozer;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class BeanMapper {

    public static final String DOZER_PATH = "dozer/DozerMapperWithDate.xml";

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper(Lists.newArrayList(DOZER_PATH));

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <A, T> Function<A, T> map(Class<T> destinationClass){
        return (a) -> map(a, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }


}
