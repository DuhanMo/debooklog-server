package org.debooklog.debooklogserver.adapter.persistence.member

import org.debooklog.debooklogserver.core.member.model.Member
import org.debooklog.debooklogserver.core.member.port.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository: MemberJpaRepository,
) : MemberRepository {
    override fun save(member: Member): Member {
        return memberJpaRepository.save(MemberEntity(member)).toModel()
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
}
