package com.mks.core;

import com.mks.core.discount.DiscountPolicy;
import com.mks.core.discount.RateDiscountPolicy;
import com.mks.core.member.MemberRepository;
import com.mks.core.member.MemberService;
import com.mks.core.member.MemberServiceImpl;
import com.mks.core.member.MemoryMemberRepository;
import com.mks.core.order.OrderService;
import com.mks.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
