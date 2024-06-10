package com.example.banksystem;

public class userData {
    private static String userId;
    private static int userBalance;
    private static String userName;

    public static String getUserId() {
        return userId;
    }

    public static int getUserBalance(){
        return userBalance;
    }

    public static void setUserId(String userId) {
        userData.userId = userId;
    }

    public static String getUserName(){
        return userName;
    }

    public static void setUserName(String userName) {
        userData.userName = userName;
    }

    public static void setUserBalance(int userBalance) {
        userData.userBalance = userBalance;
    }
}
