package com.mks.core.order;

import com.mks.core.discount.DiscountPolicy;
import com.mks.core.member.Member;
import com.mks.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//  private final DiscountPolicy fixDiscountPolicy; 조회 대상 빈 2개 이상 - 이름으로 구별 가능

    //생성자 주입시 생성자 하나인 것은 @Autowired 생략해도 됨
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //Test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
