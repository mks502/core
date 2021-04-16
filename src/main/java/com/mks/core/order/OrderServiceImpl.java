package com.mks.core.order;

import com.mks.core.discount.DiscountPolicy;
import com.mks.core.discount.FixDiscountPolicy;
import com.mks.core.member.Member;
import com.mks.core.member.MemberRepository;
import com.mks.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
