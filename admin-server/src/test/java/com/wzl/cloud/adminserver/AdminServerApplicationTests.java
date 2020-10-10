package com.wzl.cloud.adminserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.logging.Handler;

@SpringBootTest
class AdminServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void a(){
        System.out.println("ds");
    }

    @Test
    public void twoSum() {
        int[] nums = {11111, 23, 543, 322, 6566, 44444};
        int target = 55555;
        int volume = 2 << 10; //2048
        int bitNum = volume - 1; //11111111111
        int[] res = new int[volume];
        int i = 0;
//        for (int i = 0; i < nums.length; i++) {
            int c = (target - nums[0]) & bitNum;
            if (res[c] != 0) {
                System.out.println(Arrays.toString(new int[]{res[c] - 1, i}));
            }
            res[nums[i] & bitNum] = i + 1;
//        }
//        throw new IllegalArgumentException("No two sum solution");
    }


    @Test
    public void ads() {
        int[] nums = new int[]{111,444,545,222};
        int target = 555;
        int[] res = twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

    public int[] twoSum(int[] nums, int target) {
        int volume = 2<<10; //2048
        int bitNum = volume-1; //11111111111
        int[] res = new int[volume];
        for(int i=0;i<nums.length;i++){
            int c = (target-nums[i])&bitNum;
            if(res[c]!=0){
                return new int[]{res[c]-1,i};
            }
            res[nums[i]&bitNum] = i+1;
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Test
    public void dsad() {
        ListNode li = new ListNode(6);
        li.next = new ListNode(7);
        li.next.next = new ListNode(8);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(1);
        l2.next.next = new ListNode(9);

        addTwoNumbers(li, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        ListNode res = new ListNode(0);
        ListNode temp = res;
        int carry = 0;
        while (p != null || q != null) {
            int x = p.val, y = q.val;
            int sum = carry + x + y;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            carry = sum / 10;
            if(p!=null) p = p.next;
            if(q!=null) q = q.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return res.next;
    }


    @Test
    public void dsa() {
        lengthOfLongestSubstring("abcabcbb");

    }
    public int lengthOfLongestSubstring(String s) {
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
        return ans;
    }
}
