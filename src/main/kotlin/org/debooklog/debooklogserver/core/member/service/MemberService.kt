package org.debooklog.debooklogserver.core.member.service

import org.debooklog.debooklogserver.core.member.model.Member
import org.debooklog.debooklogserver.core.member.port.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Member> {
        return memberRepository.findAll()
    }

    @Transactional
    fun withdrawal(memberId: Long) {
        val member = memberRepository.getById(memberId)
        memberRepository.save(member.withdrawal())
    }
}
