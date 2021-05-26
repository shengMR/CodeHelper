package cn.com.cys.helperlib.util

/**
 * author: Damon
 * data: On 5/25/21
 */
object HelperHexUtils {

    private val DIGITS_LOWER = charArrayOf(
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    )

    private val DIGITS_UPPER = charArrayOf(
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    )

    //region 字节数组->字符
    // ========== 字节数组 -> 字符数组
    fun encodeHex(data: ByteArray?): CharArray? {
        return encodeHex(data, true)
    }

    fun encodeHex(data: ByteArray?, toLowerCase: Boolean): CharArray? {
        return encodeHex(data, if (toLowerCase) DIGITS_LOWER else DIGITS_UPPER)
    }

    private fun encodeHex(data: ByteArray?, toDigits: CharArray): CharArray? {
        if (data == null) return null
        val l = data.size
        val out = CharArray(l shl 1)
        var i = 0
        var j = 0
        while (i < l) {
            out[j++] = toDigits[0xF0 and data[i].toInt() ushr 4]
            out[j++] = toDigits[0x0F and data[i].toInt()]
            i++
        }
        return out
    }

    // ========== 字节数组 -> 十六进制字符串
    fun encodeHexStr(data: ByteArray?): String? {
        return encodeHexStr(data, true)
    }

    fun encodeHexStr(data: ByteArray?, toLowerCase: Boolean): String? {
        return encodeHexStr(data, if (toLowerCase) DIGITS_LOWER else DIGITS_UPPER)
    }

    private fun encodeHexStr(data: ByteArray?, toDigits: CharArray): String {
        val encodeHex = encodeHex(data, toDigits) ?: return ""
        return String(encodeHex)
    }

    // 与上区别在于-可添加分隔符
    fun formatHexString(data: ByteArray?): String {
        return formatHexString(data, "")
    }

    fun formatHexString(data: ByteArray?, delimiter: String): String {
        if (data == null || data.isEmpty())
            return ""
        val sb = StringBuilder()
        for (i in data.indices) {
            var hex = Integer.toHexString(data[i].toInt() and 0xFF)
            if (hex.length == 1) {
                hex = "0$hex"
            }
            sb.append(hex)
            if (i < data.size - 1 && delimiter.isNotEmpty())
                sb.append(delimiter)
        }
        return sb.toString()
    }
    //endregion

    //region 字符 -> 字节数组
    fun decodeHex(data: CharArray): ByteArray? {
        val len = data.size
        // 必须不能奇数，不然无法转换
        if (len and 0x01 != 0) {
            throw RuntimeException("Odd number of characters.")
        }
        val out = ByteArray(len shr 1)
        var i = 0
        var j = 0
        while (j < len) {
            var f: Int = toDigit(data[j], j) shl 4
            j++
            f = f or toDigit(data[j], j)
            j++
            out[i] = (f and 0xFF).toByte()
            i++
        }
        return out
    }

    private fun toDigit(ch: Char, index: Int): Int {
        val digit = Character.digit(ch, 16)
        if (digit == -1) {
            throw java.lang.RuntimeException(
                "Illegal hexadecimal character " + ch
                        + " at index " + index
            )
        }
        return digit
    }

    fun hexStringToBytes(hexString: String?): ByteArray {
        var hexString = hexString
        if (hexString == null || hexString == "") {
            return byteArrayOf()
        }
        hexString = hexString.toUpperCase()
        val length = hexString.length / 2
        val hexChars = hexString.toCharArray()
        val d = ByteArray(length)
        for (i in 0 until length) {
            val pos = i * 2
            d[i] =
                (charToByte(hexChars[pos]).toInt() shl 4 or charToByte(
                    hexChars[pos + 1]
                ).toInt()).toByte()
        }
        return d
    }

    fun charToByte(c: Char): Byte {
        return "0123456789ABCDEF".indexOf(c).toByte()
    }
    //endregion

    /**
     * int -> 16进制字符串
     */
    fun intToHex8(b: Int): String? {
        // %02X
        //  x: 表示十六进制输出
        // 02:表示不足两位前面补0
        return String.format("%02x", b)
    }

    /**
     * byte -> 位字符串
     */
    fun getBitString(by: Byte): String? {
        val sb = StringBuffer()
        sb.append(by.toInt() shr 7 and 0x1)
            .append(by.toInt() shr 6 and 0x1)
            .append(by.toInt() shr 5 and 0x1)
            .append(by.toInt() shr 4 and 0x1)
            .append(by.toInt() shr 3 and 0x1)
            .append(by.toInt() shr 2 and 0x1)
            .append(by.toInt() shr 1 and 0x1)
            .append(by.toInt() shr 0 and 0x1)
        return sb.toString()
    }

    /**
     * byte -> 位int数组
     */
    fun getBitIntArray(by: Byte): IntArray {
        return intArrayOf(
            by.toInt() shr 7 and 0x01,
            by.toInt() shr 6 and 0x01,
            by.toInt() shr 5 and 0x01,
            by.toInt() shr 4 and 0x01,
            by.toInt() shr 3 and 0x01,
            by.toInt() shr 2 and 0x01,
            by.toInt() shr 1 and 0x01,
            by.toInt() shr 0 and 0x01
        )
    }
}

fun main() {
    val formatHexString = HelperHexUtils.formatHexString(
        byteArrayOf(
            0x4F,
            0xF4.toByte(),
            0x4F,
            0xF4.toByte(),
            0x4F,
            0xF4.toByte()
        )
    )
    println(formatHexString)
    var decodeHex = HelperHexUtils.decodeHex(formatHexString.toCharArray())
    println(HelperHexUtils.formatHexString(decodeHex))
    decodeHex = HelperHexUtils.hexStringToBytes(HelperHexUtils.formatHexString(decodeHex))
    println(HelperHexUtils.formatHexString(decodeHex))

    println(HelperHexUtils.intToHex8(23))
    println(HelperHexUtils.getBitString(0xF0.toByte()))
    val bitIntArray = HelperHexUtils.getBitIntArray(0xF0.toByte())
    for (index in bitIntArray.indices) {
        print(bitIntArray[index].toString())
    }

}