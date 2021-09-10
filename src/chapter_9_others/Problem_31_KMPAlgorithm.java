package chapter_9_others;

import java.util.Arrays;

/**
 * KMP�㷨������Str1�ַ������Ƿ����Str2�ַ����������ص�һ��λ��
 * ˼·��
 * 1. �Ȼ�ȡ�ַ���str2��next���飬next������str2���ַ�����ȳ�
 *    ����next2����洢�����ݵ�˵����
 *    String str2 = "abcdeabcd";
 *    charArr[] = ['a','b','c','d','e','a','b','c','d'];
 *    nextArr: [������������]
 *    nextArr����ĵ�0��λ�ù涨Ϊ-1����һ��λ�ù涨Ϊ0������λ����ѭ���¹淶����
 *    nextArr����Ϊiλ�õ�ֵ����charArr��0~i-1λ�õģ���0λ��Ϊ��ʼ����ֱ��i-1(������i-1λ��)��ǰ׺�ַ���( [0, i-1) ) = ��i-1λ��Ϊ��β����ֱ��0(������0λ��)�ĺ�׺�ַ���( (0,i-1] )
 *    ����˵����
 *    ����str2�ַ����±�Ϊ4��λ�ã���charArr[4] = 'd',
 *      ��Ӧ��nextArr��nextArr[4]��ֵΪ charArr[0~3]λ�õ��ַ�������0��ͷ���Ҳ�����3��ǰ׺�ַ���������3��β���󲻰���0�ĺ�׺�ַ�������󳤶ȣ�����δ�ҵ�����nextArr[4]=0
 *    ����str2�ַ����±�Ϊ8��λ�ã���charArr[8] = 'd',
 *      ��Ӧ��nextArr��nextArr[8]��ֵΪ charArr[0~7]λ�õ��ַ�������0��ͷ���Ҳ�����7��ǰ׺�ַ���������7��β���󲻰���0�ĺ�׺�ַ�������󳤶ȣ������ҵ���'abc'����nextArr[8]=3
 *    ���ι淶�����Եó�"abcdeabcd"
 *    ['a','b','c','d','e','a','b','c','d'];
 *    ��Ӧ��nextArr����Ϊ��
 *    [ -1 , 0 , 0 , 0 , 0 , 0 , 1 , 2 , 3 ]
 *    ��ο������nextArr�ַ������飺
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
