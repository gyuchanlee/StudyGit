package com.dodo.bootpractice.repository;

import com.dodo.bootpractice.domain.Member;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dodo.bootpractice.domain.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> getMembers() {
        return queryFactory
                .select(member) // QMember.member
                .from(member)
                .where(member.rating.between(1, 5))
                .orderBy(member.id.desc()) // OrderSpecifier인 member.id.desc()을 활용해서 orderBy절 작성.
                .fetch();
    }

    @Override
    public Member getMemberById(Long id) {
        return queryFactory
                .select(member)
                .from(member)
                .where(member.height.goe(150))
                .fetchOne();
    }
}
