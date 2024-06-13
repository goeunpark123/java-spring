package com.ohgiraffers.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalAccount implements Account {
    private final int bankCode;
    private final String accNo;
    private int balance;

    public PersonalAccount(int bankCode, String accNo) {
        this.bankCode = bankCode;
        this.accNo = accNo;
    }

    @Override
    public String getBalance() {
        return this.accNo + " 계좌의 현재 잔액은 " + this.balance + "원입니다.";
    }

    @Override
    public String deposit(int money) {
        String str = "";

        if(money >= 0) {
            this.balance += money;
            str = money + "원이 입급되었습니다.";
        } else {
            str = "잘못된 입력";
        }

        return str;
    }

    @Override
    public String withDraw(int money) {
        String str = "";
        if(this.balance >= money) {
            this.balance -= money;
            str = money + "원이 출금되었습니다.";
        } else {
            str = "잔액 부족";
        }

        return str;
    }
}
