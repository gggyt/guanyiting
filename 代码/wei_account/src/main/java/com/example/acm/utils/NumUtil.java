package com.example.acm.utils;

public class NumUtil {

    public static boolean isPhone(String str) {
        if (StringUtils.isNull(str)) {
            return false;
        }
        return str.matches("^1[34789][0-9]{9}$");
    }

    public static boolean isUserName(String str) {
        if (StringUtils.isNull(str)) {
            return false;
        }
        return str.matches("^.{3,20}$");
    }
    public static boolean isStudent(String str) {
        if (StringUtils.isNull(str)) {
            return false;
        }
        return str.matches("^[0-9]{10}$");
    }

    public static boolean isPassword(String str) {
        if (StringUtils.isNull(str)) {
            return false;
        }
        return str.matches("^.{6,20}$");
    }

    public static void main(String[] args) {
        System.out.println(isPhone("17716164149"));
        System.out.println(isPhone("12771616414"));
        System.out.println(isUserName("xx"));
        System.out.println(isUserName("xxx1"));
        System.out.println(isUserName("啊啊"));
        System.out.println(isUserName("啊啊-"));
        System.out.println(isUserName("啊啊啊"));
        System.out.println(isUserName("啊啊啊"));
        System.out.println(isPassword("啊啊啊aaa哎哎哎"));
        System.out.println(isUserName("啊啊啊啊啊啊"));
        System.out.println(isStudent("2015081131"));
        System.out.println(isStudent("201081131"));
        System.out.println(isStudent("20108113111"));
    }
}
