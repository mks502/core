package com.mks.core;

import com.mks.core.discount.DiscountPolicy;
import com.mks.core.discount.FixDiscountPolicy;
import com.mks.core.discount.RateDiscountPolicy;
import com.mks.core.member.MemberRepository;
import com.mks.core.member.MemberService;
import com.mks.core.member.MemberServiceImpl;
import com.mks.core.member.MemoryMemberRepository;
import com.mks.core.order.OrderService;
import com.mks.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService (){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy (){
        return new RateDiscountPolicy();
    }
}
