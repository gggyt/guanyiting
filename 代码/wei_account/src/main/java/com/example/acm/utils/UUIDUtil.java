package com.example.acm.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
    /**
     * 获得4个长度的十六进制的UUID
     * @return UUID
     */ public static String get4UUID(){ UUID id= UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[1]; } /**
     * 获得8个长度的十六进制的UUID
     * @return UUID
     */ public static String get8UUID(){ UUID id=UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[0]; } /**
     * 获得12个长度的十六进制的UUID
     * @return UUID
     */ public static String get12UUID(){ UUID id=UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[0]+idd[1]; } /**
     * 获得16个长度的十六进制的UUID
     * @return UUID
     */ public static String get16UUID(){ UUID id=UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[0]+idd[1]+idd[2]; } /**
     * 获得20个长度的十六进制的UUID
     * @return UUID
     */ public static String get20UUID(){ UUID id=UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[0]+idd[1]+idd[2]+idd[3]; } /**
     * 获得24个长度的十六进制的UUID
     * @return UUID
     */ public static String get24UUID(){ UUID id=UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[0]+idd[1]+idd[4]; } /**
     * 获得32个长度的十六进制的UUID
     * @return UUID
     */ public static String get32UUID(){ UUID id=UUID.randomUUID(); String[] idd=id.toString().split("-"); return idd[0]+idd[1]+idd[2]+idd[3]+idd[4]; }

    public static String getNumUUID(){
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<13;i++)
        {
            sb.append(rand.nextInt(10));
        }
        String data=sb.toString();
        System.out.println(16+" random data: "+data);
        return data;
    }

    public static void main(String[] args) {
        getNumUUID();
    }
}
