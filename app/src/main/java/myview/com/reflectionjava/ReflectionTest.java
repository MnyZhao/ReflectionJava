package myview.com.reflectionjava;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import myview.com.model.User;

/**
 * Crate by E470PD on 2019/3/11
 */
public class ReflectionTest {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String args[]) {
        Class userClass = User.class;
        System.out.println("类名：" + userClass.getName());
//        getFildes(userClass);
//        getMethod(userClass);
//        getMethodInfo(userClass);
//        accessPrivateMethod(userClass);
//        accessPrivateField(userClass);
        createList();
    }

    /**
     * 获取变量名称
     *
     * @param className
     */
    public static void getFildes(Class className) {
        //获取类的成员变量 public 包括从父类继承的
        Field[] files = className.getFields();
        for (int i = 0; i < files.length; i++) {
            System.out.println("获取public变量->本类和父类声明的->getFields() :  " + files[i].getName());
        }
        //获取类的成员变量 获取所有本类声明的变量（不问访问权限）
        Field[] fields = className.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("获取任何权限变量->本类声明的->getDeclaredFields() :  " + fields[i].getName());
        }
        System.out.println("ReflectionTest.getFildes->>>>>>>>>>>>>>>>>getFildes");
    }

    /**
     * 获取所有的方法
     *
     * @param className
     */
    public static void getMethod(Class className) {
        Method[] methods = className.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("获取public方法->本类和父类->getMethods()" + methods[i].getName());
        }
        Method[] methods1 = className.getDeclaredMethods();
        for (int i = 0; i < methods1.length; i++) {
            System.out.println("获取所有方法->本类->getDeclaredMethods()" + methods1[i].getName());
        }
        System.out.println("ReflectionTest.getMethod->>>>>>>>>>>>>>>>getMethod");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getMethodInfo(Class className) {
        Method[] methods = className.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            //获取方法权限
            int modifiers = method.getModifiers();
            System.out.println(method.getName() + ":方法访问权限 : " + Modifier.toString(modifiers));
            //获取返回类型
            Class returnType = method.getReturnType();
            System.out.println(method.getName() + ":方法返回类型:" + returnType);
            //获取并输出方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                String paramterName = parameter.getName();
                String type = parameter.getType().getName();
                System.out.println(method.getName() + ":方法参数类型:" + type + "->参数名称：" + paramterName);
            }
            //获取并输出方法抛出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length == 0) {
                System.out.println(method.getName() + "方法抛出的异常：");
            }
            for (int j = 0; j < exceptionTypes.length; j++) {
                System.out.println(method.getName() + "方法抛出的异常：" + exceptionTypes[j].getName());
            }
            System.out.println("ReflectionTest.getMethodInfo->>>>>>>>>>>>getMethodInfo");
        }
    }

    /**
     * 访问私有方法
     *
     * @param className
     */
    public static void accessPrivateMethod(Class className) {
        try {
            Method privateMethod = className.getDeclaredMethod("setName", String.class);
            Object instance = className.newInstance();//获取一个类的实例 相当于下面的new呢哇
            User user = new User();
            //3. 开始操作方法
            if (privateMethod != null) {
                //获取私有方法的访问权
                //只是获取访问权，并不是修改实际权限
                privateMethod.setAccessible(true);

                //使用 invoke 反射调用私有方法
                //privateMethod 是获取到的私有方法
                //instance 要操作的对象
                //后面两个参数传实参
                privateMethod.invoke(user, "天王盖地虎");
                System.out.println("SetName" + user.getName());
                System.out.println("访问私有方法->>>>>>>>>>>>>>>>>>>>>>");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 访问私有变量
     *
     * @param className
     */
    private static void accessPrivateField(Class className) {
        try {
            Field privateField = className.getDeclaredField("name");
            //3. 操作私有变量
            if (privateField != null) {
                //获取私有变量的访问权
                privateField.setAccessible(true);

                //修改私有变量，并输出以测试
                User user = new User();
                System.out.println("NameDefault : " + user.getName());

                //调用 set(object , value) 修改变量的值
                //privateField 是获取到的私有变量
                //user 要操作的对象
                //"Modified" 为要修改成的值
                privateField.set(user, "小鸡炖蘑菇");
                System.out.println("NameChange : " + user.getName());
                System.out.println("访问私有常量->>>>>>>>>>>>>>>>>>>>>>");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void createList() {
        Class sc = String.class;
        Object array = Array.newInstance(sc, 10);
        Array.set(array, 1, "one");
        Array.set(array, 2, "two");
        Array.set(array, 3, "three");
        Array.set(array, 4, "four");
        Array.set(array, 5, "five");
        System.out.println("ReflectionTest.createList--->>>>>>>>" + Array.get(array, 3));
    }
}
