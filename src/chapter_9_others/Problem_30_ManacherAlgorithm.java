package chapter_9_others;

/**
 * �������㷨�������������Ӵ��������س���
 * �㷨�����輰˼�룺
 * 1. ��һ�������ַ������зָ���䴦��.
 * 		���磺 abc, �����Ϊ #a#b#c#�� ������3��Ϊ7
 * 		���磺 abcd, �����Ϊ #a#b#c#d#��������4��Ϊ9
 * 	   ����������ԭʼ�ַ�������Ϊ��������ż�����������ַ�����Ϊ����
 * 2. �ڶ�������ÿ���ַ�Ϊ���ģ��������İ뾶
 * 	  ������
 * 	  1. �趨����R����¼��ǰ���Ļ��İ뾶���ұ߽�
 * 	  	 �趨����cur����¼��ǰλ��
 * 	  2. ��ÿ��λ�õ������İ뾶ʱ������Ҫ�жϸ�λ��cur��R��λ�ù�ϵ
 * 	      1����cur>=Rʱ��ʹ�ñ�����ʽ����curΪ���������ҷ���ȶԣ��ҳ��µĻ��İ뾶��������Rλ�ã�cur����
 * 	      2����cur<R���ڣ�����Խ����Ż����٣�
 * 	      	 ����R��Cλ�õĻ��İ뾶���ұ߽磬����C��ߴ���һ����R�ԳƵ�L�㣬���Ҵ�ʱ��cur�ض���C~R�м��ĳ��λ��
 * 	      	 ������L~C�м�����ҵ�һ��cur�ĶԳƵ㣬cur'
 * 	      	 L -- cur' -- C -- cur -- R
 * 	      	 ��cur'�������İ뾶����߽���L���ڣ���ôcur��������İ뾶 = cur'�������İ뾶
 * 	      	 ��cur'�������İ뾶ǡ������߽�L��ʱ����ôcur��������İ뾶ΪR-cur
 * 	      	 ��cur'�������İ뾶����߽�L�����ʱ����ôcur�����С���İ뾶��R-cur��Ȼ����Ҫ��L-1�������αȽ�R+1�������ҵ�λ�ã�ֱ���ҵ�cur�������İ뾶���������R��R����Ϊ�µ��ұ߽�
 * 	  �����������򣬱�����󼴿���������İ뾶
 *
 */
public class Problem_30_ManacherAlgorithm {

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	public static int maxLcpsLength(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;
	}

	public static String shortestEnd(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int maxContainsEnd = -1;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			if (pR == charArr.length) {
				maxContainsEnd = pArr[i];
				break;
			}
		}
		char[] res = new char[str.length() - maxContainsEnd + 1];
		for (int i = 0; i < res.length; i++) {
			res[res.length - 1 - i] = charArr[i * 2 + 1];
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		String str1 = "abc1234321ab";
		System.out.println(maxLcpsLength(str1));

		String str2 = "abcd123321";
		System.out.println(shortestEnd(str2));

	}

}
