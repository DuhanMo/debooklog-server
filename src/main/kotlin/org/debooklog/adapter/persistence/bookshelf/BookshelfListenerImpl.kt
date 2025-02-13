package org.debooklog.adapter.persistence.bookshelf

import org.debooklog.core.bookshelf.model.Bookshelf
import org.debooklog.core.bookshelf.model.BookshelfNameGenerator
import org.debooklog.core.bookshelf.port.BookshelfListener
import org.debooklog.core.bookshelf.port.BookshelfRepository
import org.debooklog.core.member.port.MemberRepository
import org.debooklog.core.member.service.MemberCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant.now

@Component
class BookshelfListenerImpl(
    private val bookshelfRepository: BookshelfRepository,
    private val memberRepository: MemberRepository,
) : BookshelfListener {
    @EventListener
    @Transactional
    override fun handle(event: MemberCreatedEvent) {
        val member = memberRepository.getById(event.memberId)
        val bookshelfName = BookshelfNameGenerator.generate(member.name)
        val bookshelf =
            Bookshelf(
                memberId = member.id,
                name = bookshelfName,
                imageUrl = member.profileImage,
                now = now(),
            )
        bookshelfRepository.save(bookshelf)
    }
}
