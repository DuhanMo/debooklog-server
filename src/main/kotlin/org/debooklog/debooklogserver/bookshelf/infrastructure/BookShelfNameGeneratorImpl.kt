package org.debooklog.debooklogserver.bookshelf.infrastructure

import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfNameGenerator
import org.springframework.stereotype.Component
import kotlin.random.Random

private val ADJECTIVES =
    listOf(
        "심심한", "아름다운", "멋진", "즐거운", "행복한", "기쁜", "슬픈", "화난", "평화로운", "힘찬",
        "신비로운", "무서운", "귀여운", "커다란", "작은", "따뜻한", "차가운", "용감한", "겁많은",
    )

private val NOUNS =
    listOf(
        "코끼리", "자동차", "책", "나무", "고양이", "강아지", "새", "하늘", "바다", "별",
        "꽃", "돌", "산", "강", "호수", "물고기", "개구리", "토끼", "사자", "호랑이",
    )

@Component
class BookShelfNameGeneratorImpl : BookShelfNameGenerator {
    override fun generate(): String {
        return "${ADJECTIVES[Random.nextInt(ADJECTIVES.size)]} ${NOUNS[Random.nextInt(NOUNS.size)]}의 책장"
    }
}
