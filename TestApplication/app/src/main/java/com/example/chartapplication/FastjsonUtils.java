package com.example.chartapplication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class FastjsonUtils {

    private FastjsonUtils() {
    }
    private static final String TAG = FastjsonUtils.class.getSimpleName();
    public static String toJson(final Object object) {
        if (object != null) {
            try {
                return JSON.toJSONString(object);
            } catch (Exception e) {
            }
        }
        return null;
    }
    public static <T> T fromJson(final String json, final Class<T> classOfT) {
        if (json != null) {
            try {
                return JSON.parseObject(json, classOfT);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static <T> T fromJson(final String json, final Type typeOfT) {
        if (json != null) {
            try {
                return JSON.parseObject(json, typeOfT);
            } catch (Exception e) { 
            }
        }
        return null;
    }

    public static boolean isJSON(final String json) {
        try {
            Object object = JSON.parse(json);
            return (object != null);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isJSONObject(final String json) {
        try {
            Object object = JSON.parse(json);
            return (object instanceof JSONObject);
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isJSONArray(final String json) {
        try {
            Object object = JSON.parse(json);
            return (object instanceof JSONArray);
        } catch (Exception e) {
        }
        return false;
    }

    public static String toJsonIndent(final String json) {
        if (json != null) {
            try {
                Object object = JSON.parse(json, Feature.OrderedField);
                if (object instanceof JSONObject) {
                    return JSONObject.toJSONString(object, true);
                } else if (object instanceof JSONArray) {
                    return JSONArray.toJSONString(object, true);
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static String toJsonIndent(final Object object) {
        if (object != null) {
            try {
                return toJsonIndent(toJson(object));
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Type getArrayType(final Type type) {
        return new GenericArrayType() {
            @Override
            public Type getGenericComponentType() {
                return type;
            }
        };
    }

    public static Type getListType(final Type type) {
        return new ParameterizedTypeImpl(new Type[]{type}, null, List.class);
    }


    public static Type getSetType(final Type type) {
        return new ParameterizedTypeImpl(new Type[]{type}, null, Set.class);
    }

    public static Type getMapType(final Type keyType, final Type valueType) {
        return new ParameterizedTypeImpl(new Type[]{keyType, valueType}, null, Map.class);
    }

    public static Type getType(final Type rawType, final Type... typeArguments) {
        return new ParameterizedTypeImpl(typeArguments, null, rawType);
    }
}