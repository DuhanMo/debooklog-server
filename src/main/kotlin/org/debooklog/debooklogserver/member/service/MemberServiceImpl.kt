package org.debooklog.debooklogserver.member.service

import org.debooklog.debooklogserver.member.controller.port.MemberService
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.MemberCreateCommand
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
) : MemberService {
    @Transactional
    override fun create(command: MemberCreateCommand) {
        memberRepository.save(Member.from(command))
    }
}
