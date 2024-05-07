package org.debooklog.debooklogserver.bookshelf.domain

import org.springframework.stereotype.Component
import kotlin.random.Random

private val ADJECTIVES =
    listOf(
        "심심한", "아름다운", "멋진", "즐거운", "행복한", "기쁜", "슬픈", "화난", "평화로운", "힘찬",
        "신비로운", "무서운", "귀여운", "커다란", "작은", "따뜻한", "차가운", "용감한", "겁많은",
    )

@Component
class BookshelfNameGenerator {
    fun generate(memberName: String): String {
        return "${ADJECTIVES[Random.nextInt(ADJECTIVES.size)]} ${memberName}의 책장"
    }
}
