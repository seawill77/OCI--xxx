package leetcode_1To300;

/**
 * 本代码来自 Cspiration，由 @Cspiration 提供
 * 题目来源：http://leetcode.com
 * - Cspiration 致力于在 CS 领域内帮助中国人找到工作，让更多海外国人受益
 * - 现有课程：Leetcode Java 版本视频讲解（1-900题）（上）（中）（下）三部
 * - 算法基础知识（上）（下）两部；题型技巧讲解（上）（下）两部
 * - 节省刷题时间，效率提高2-3倍，初学者轻松一天10题，入门者轻松一天20题
 * - 讲师：Edward Shi
 * - 官方网站：https://cspiration.com
 * - 版权所有，转发请注明出处
 */
public class _44_WildcardMatching {
    /**
     * Implement wildcard pattern matching with support for '?' and '*'.

     '?' Matches any single character.
     '*' Matches any sequence of characters (including the empty sequence).

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "*") → true
     isMatch("aa", "a*") → true
     isMatch("ab", "?*") → true
     isMatch("aab", "c*a*b") → false

     "bbarc" match = 3 sp = 3
     "*c" star = 0 pp = 1

     time : O(m * n)
     space : O(1)

     * @param s
     * @param p
     * @return
     
     
     public boolean isMatch(String s, String p) {
     
     
       String[] parttens = p.split("\\*");
        
        
        int end = 0; 
        
        for (String pattern: parttens) {
            
            int index = s.indexOf(pattern, end);
            
            if (end == 0 && index != 0) {
                if (p.charAt(0) != '*') {
                    return false;
                }
            }
            
            if (index != -1) {
                end = index + pattern.length();
            } else {
                return false;
            }
            
        }
        
        if (end != s.length()) {
            if (p.charAt(p.length() - 1) == '*') {
                return true;
            }
        }
        return false;
	
     }
     
     
     test case  
     
     	"abcabczzzde"
	"*abczzzde" 
     */
    public boolean isMatch(String s, String p) {
        int sp = 0;
        int pp = 0;
        int match = 0;
        int star = -1;
        while (sp < s.length()) {
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if (pp < p.length() && p.charAt(pp) == '*') {
                star = pp;
                match = sp;
                pp++;
            } else if (star != -1) {
                pp = star + 1;
                match++;
                sp = match;
            } else return false;
        }
        while (pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }
        return pp == p.length();
    }
}


//coner case: "abcabczzzde"    "*abc???de*"



public boolean isMatch_2d_method(String s, String p) {
	int m=s.length(), n=p.length();
	boolean[][] dp = new boolean[m+1][n+1];
	dp[0][0] = true;
	for (int i=1; i<=m; i++) {
		dp[i][0] = false;
	}
	
	for(int j=1; j<=n; j++) {
		if(p.charAt(j-1)=='*'){
			dp[0][j] = true;
		} else {
			break;
		}
	}
	
	for(int i=1; i<=m; i++) {
		for(int j=1; j<=n; j++) {
			if (p.charAt(j-1)!='*') {
				dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?');
			} else {
				dp[i][j] = dp[i-1][j] || dp[i][j-1];
			}
		}
	}
	return dp[m][n];
}





//一个 *  only

class Solution {
    public boolean isMatch(String s, String p) {
int sl = 0; 
        int pl = 0;
        int sr = s.length() - 1;
        int pr = p.length() - 1;
        boolean isStar = false;
        
        
        while (pl < p.length()) {
            if (p.charAt(pl) == '*') {
                isStar = true;
                break;
            }
            if (sl < s.length() && s.charAt(sl++) != p.charAt(pl++)) return false;
        }
        
        while (pr >= 0) {
            if (p.charAt(pr) == '*') break;
            if (sr >= 0 && s.charAt(sr--) != p.charAt(pr--)) return false;
        }
        
        
        return isStar ? s.length() >= p.length() - 1 : s.length() == p.length();
    }   
