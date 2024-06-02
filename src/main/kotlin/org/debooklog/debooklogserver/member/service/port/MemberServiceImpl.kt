package org.debooklog.debooklogserver.member.service.port

import org.debooklog.debooklogserver.member.controller.port.MemberService
import org.debooklog.debooklogserver.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
) : MemberService {
    @Transactional(readOnly = true)
    override fun findAll(): List<Member> {
        return memberRepository.findAll()
    }

    @Transactional
    override fun withdrawal(memberId: Long) {
        val member = memberRepository.getById(memberId)
        memberRepository.save(member.withdrawal())
    }
}
