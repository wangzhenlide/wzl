package com.wzl.cloud.adminserver;

import javax.xml.stream.events.Characters;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author wzl
 * @Date 2020/9/14 16:15
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        Set<Character> occ = new HashSet<>();

        int n = s.length();
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && occ.contains(s.charAt(rk + 1))) {
                rk++;
            }
            System.out.println(n - rk + 1);
            ans = Math.max(ans, rk - i + 1);
        }
        System.out.println(ans);
    }



}
