package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.domain.BookshelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfListener
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.debooklog.debooklogserver.member.domain.MemberCreatedEvent
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now

@Component
class BookshelfListenerImpl(
    private val bookshelfNameGenerator: BookshelfNameGenerator,
    private val bookshelfRepository: BookshelfRepository,
    private val memberRepository: MemberRepository,
) : BookshelfListener {
    @EventListener
    @Transactional
    override fun handle(event: MemberCreatedEvent) {
        val member = memberRepository.getById(event.memberId)
        val bookshelfName = bookshelfNameGenerator.generate(member.name)
        val bookshelf =
            Bookshelf(
                memberId = member.id!!,
                name = bookshelfName,
                now = now(),
            )
        bookshelfRepository.save(bookshelf)
    }
}
