package com.mks.core.autowired;

import com.mks.core.AutoAppConfig;
import com.mks.core.discount.DiscountPolicy;
import com.mks.core.member.Grade;
import com.mks.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> discountServiceMap;
        private final List<DiscountPolicy> discountPolicies;

        public DiscountService(Map<String, DiscountPolicy> discountServiceMap, List<DiscountPolicy> discountPolicies) {
            this.discountServiceMap = discountServiceMap;
            this.discountPolicies = discountPolicies;
            System.out.println("discountServiceMap = " + this.discountServiceMap);
            System.out.println("discountPolicies = " + discountPolicies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = discountServiceMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
