package com.mks.core.discount;

import com.mks.core.member.Grade;
import com.mks.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
// @Primary 우선적으로 들어가도록 ex) 메인 db 보조 db 있을 때 메인에 Primary
// @Primary 간 @Qualifier 우선순위는 @Qualifier가 더 높음!
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        else {
            return 0;
        }
    }
}
