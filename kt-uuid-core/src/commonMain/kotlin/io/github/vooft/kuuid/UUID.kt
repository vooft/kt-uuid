package io.github.vooft.kuuid

import org.kotlincrypto.SecureRandom
import kotlin.experimental.and
import kotlin.experimental.or


class UUID(val mostSignificantBits: Long, val leastSignificantBits: Long) {

    override fun toString(): String {
        return buildString(36) {
            fun appendHex(value: Long, bytesOffset: Int, bytesLength: Int) {
                val bitsOffset = bytesOffset * 8
                val bitsLength = bytesLength * 8

                val withoutPrefix = value shl bitsOffset ushr bitsOffset
                val actual = withoutPrefix ushr (64 - bitsOffset - bitsLength)
                append(actual.toString(16).padStart(bytesLength * 2, '0'))
            }

            appendHex(mostSignificantBits, 0, 4)
            append('-')
            appendHex(mostSignificantBits, 4, 2)
            append('-')
            appendHex(mostSignificantBits, 6, 2)
            append('-')
            appendHex(leastSignificantBits, 0, 2)
            append('-')
            appendHex(leastSignificantBits, 2, 6)
        }
    }

    companion object {
        private val random = SecureRandom()

        fun randomUUID(): UUID {
            val buffer = random.nextBytesOf(16)
            buffer[6] = buffer[6] and 0x0f or 0x40 // set version to 4
            buffer[8] = buffer[8] and 0x3f or 0x80.toByte() // set variant to 10

            return UUID(mostSignificantBits = buffer.toLong(0), leastSignificantBits = buffer.toLong(8))
        }

        fun fromString(uuid: String): UUID {
            require(uuid.length == 36)
            require(uuid[8] == '-' && uuid[13] == '-' && uuid[18] == '-' && uuid[23] == '-')

            val mostSignificantBits = (uuid.substring(0, 8).toLong(16) shl 32) or
                    (uuid.substring(9, 13).toLong(16) shl 16) or
                    (uuid.substring(14, 18).toLong(16))

            val leastSignificantBits = uuid.substring(19, 23).toLong(16) shl 48 or
                    uuid.substring(24, 36).toLong(16)

            return UUID(mostSignificantBits, leastSignificantBits)
        }
    }
}

private fun ByteArray.toLong(offset: Int): Long {
    require(size - offset >= 8)

    var result = 0L
    for (i in 0 until 8) {
        result = result shl 8 or (this[offset + i].toLong() and 0xff)
    }

    return result
}
