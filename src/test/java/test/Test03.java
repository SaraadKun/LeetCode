package test;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Title: Test03
 * @Package:test
 * @Description:
 * @author: saraad
 * @date: 2021/9/13 3:30 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
@Data
@AllArgsConstructor
class OBJ {
    private String name;
    private String id;

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
}
public class Test03 {


    public static void main(String[] args) throws Exception {

        List<OBJ> list = getData();
        System.out.println(JSON.toJSONString(list));
        List<OBJ> res = list.stream().filter(distinctByKeys(OBJ::getName, OBJ::getId)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(res));
    }

    private static List<OBJ> getData() {
        List<OBJ> list = new ArrayList<>();
        int i = 0;
        BreakPoint:
        for ( i = 0; i < 5; i++) {
            break BreakPoint;
        }
        list.add(new OBJ("a", "1"));
        list.add(new OBJ("a", "1"));
        list.add(new OBJ("a","2"));
        list.add(new OBJ("b", "1"));
        list.add(new OBJ("c", "1"));
        list.add(new OBJ("d", "1"));
        return list;
    }

    private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
        return t -> {
            List<?> keys = Stream.of(keyExtractors)
                    .map(key -> key.apply(t))
                    .collect(Collectors.toList());
            System.out.println(keys.hashCode());
            return seen.put(keys, Boolean.TRUE) == null;
        };
    }
    //        return t -> {
    //            String keys = Stream.of(keyExtractors)
    //                    .map(key -> key.apply(t))
    //                    .map(Object::toString)
    //                    .collect(Collectors.joining(","));
    //            return seen.put(keys, Boolean.TRUE) == null;
    //        };

    public static <T> T mapToObject(Map<String, String> map, Class<T> beanClass) throws Exception {
        if (map == null || map.size() < 1) {
            return null;
        }
        T obj = beanClass.newInstance();
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod) || map.get(field.getName()) == null) {
                continue;
            }
            field.setAccessible(true);
            Object value = dealDataType(field.getType(), map.get(field.getName()));
            field.set(obj, value);
        }
        return  obj;
    }

    private static Object dealDataType(Class<?> type, String src) {
//        if (Boolean.class == type) {
//            return Boolean.parseBoolean(src);
//        } else {
//            return src;
//        }
        return true;
    }

}
@Data
class Demo {
    private Boolean var1;
    private String var2;
}
