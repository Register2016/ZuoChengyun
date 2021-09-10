package chapter_9_others;

/**
 * 马拉车算法，解决求最长回文子串，并返回长度
 * 算法处理步骤及思想：
 * 1. 第一步，将字符串进行分割填充处理.
 * 		例如： abc, 处理后为 #a#b#c#， 长度由3变为7
 * 		例如： abcd, 处理后为 #a#b#c#d#，长度由4变为9
 * 	   这样，不管原始字符串长度为奇数还是偶数，处理后的字符串恒为奇数
 * 2. 第二步，以每个字符为中心，求最大回文半径
 * 	  方法：
 * 	  1. 设定变量R，记录当前最大的回文半径的右边界
 * 	  	 设定变量cur，记录当前位置
 * 	  2. 求每个位置的最大回文半径时，先需要判断该位置cur与R的位置关系
 * 	      1）当cur>=R时则使用暴力方式，以cur为中心向左右方向比对，找出新的回文半径，并更新R位置，cur右移
 * 	      2）当cur<R以内，则可以进行优化加速：
 * 	      	 假设R是C位置的回文半径的右边界，则在C左边存在一个与R对称的L点，并且此时的cur必定在C~R中间的某个位置
 * 	      	 所以在L~C中间可以找到一个cur的对称点，cur'
 * 	      	 L -- cur' -- C -- cur -- R
 * 	      	 当cur'的最大回文半径在左边界在L以内，那么cur点的最大回文半径 = cur'的最大回文半径
 * 	      	 当cur'的最大回文半径恰好在左边界L上时，那么cur点的最大回文半径为R-cur
 * 	      	 当cur'的最大回文半径在左边界L的左边时，那么cur点的最小回文半径是R-cur，然后需要从L-1往左依次比较R+1依次往右的位置，直到找到cur的最大回文半径，如果超过R则将R更新为新的右边界
 * 	  根据上述规则，遍历完后即可求得最大回文半径
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
