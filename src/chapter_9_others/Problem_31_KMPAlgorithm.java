package chapter_9_others;

import java.util.Arrays;

/**
 * KMP算法，查找Str1字符串中是否包含Str2字符串，并返回第一个位置
 * 思路：
 * 1. 先获取字符串str2的next数组，next数组与str2的字符数组等长
 *    关于next2数组存储的数据的说明：
 *    String str2 = "abcdeabcd";
 *    charArr[] = ['a','b','c','d','e','a','b','c','d'];
 *    nextArr: [。。。。。。]
 *    nextArr数组的第0个位置规定为-1，第一个位置规定为0，其他位置遵循以下规范生成
 *    nextArr索引为i位置的值，是charArr中0~i-1位置的，以0位置为开始向右直到i-1(不包含i-1位置)的前缀字符串( [0, i-1) ) = 以i-1位置为结尾向左直到0(不包含0位置)的后缀字符串( (0,i-1] )
 *    举例说明：
 *    比如str2字符串下标为4的位置，即charArr[4] = 'd',
 *      对应在nextArr中nextArr[4]的值为 charArr[0~3]位置的字符串，以0开头向右不包含3的前缀字符串，和以3结尾向左不包含0的后缀字符串的最大长度，发现未找到，即nextArr[4]=0
 *    比如str2字符串下标为8的位置，即charArr[8] = 'd',
 *      对应在nextArr中nextArr[8]的值为 charArr[0~7]位置的字符串，以0开头向右不包含7的前缀字符串，和以7结尾向左不包含0的后缀字符串的最大长度，发现找到是'abc'，即nextArr[8]=3
 *    依次规范，可以得出"abcdeabcd"
 *    ['a','b','c','d','e','a','b','c','d'];
 *    对应的nextArr数组为：
 *    [ -1 , 0 , 0 , 0 , 0 , 0 , 1 , 2 , 3 ]
 *    如何快速求解nextArr字符串数组：
 *
 *
 *
 *
 *
 *
 */
public class Problem_31_KMPAlgorithm {



    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));

        String test = "abcdeabcd";
        System.out.println(Arrays.toString(getNextArray(test.toCharArray())));

    }

}
