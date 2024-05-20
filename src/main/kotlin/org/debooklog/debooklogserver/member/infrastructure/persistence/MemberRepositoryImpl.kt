package org.debooklog.debooklogserver.member.infrastructure.persistence

import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
) : MemberRepository {
    override fun save(member: Member): Member {
        return memberJpaRepository.save(MemberEntity.from(member)).toModel()
    }

    override fun getById(id: Long): Member {
        return memberJpaRepository.findByIdOrNull(id)
            ?.toModel()
            ?: throw NoSuchElementException()
    }

    override fun findAll(): List<Member> {
        return memberJpaRepository.findAll().map(MemberEntity::toModel)
    }

    override fun findByEmail(email: String): Member? {
        return memberJpaRepository.findByEmail(email)?.toModel()
    }

    override fun deleteById(memberId: Long) {
        memberJpaRepository.deleteById(memberId)
    }
}
