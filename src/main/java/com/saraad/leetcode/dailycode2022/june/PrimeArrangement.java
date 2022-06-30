package com.saraad.leetcode.dailycode2022.june;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 30-06-2022 01:03
 */

public class PrimeArrangement {

    long m = (long) 1e9 + 7;

    public int numPrimeArrangements(int n) {
        //计数小于等于n的质数cnt，ans = A(cnt, cnt) * A(n-cnt, n-cnt) % m; 注意取模时不要越界;
        int n1 = countPrime(n);
        return (int)(factorial(n1) * factorial(n - n1) % m);
    }

    long factorial(int n) {
        long ans = 1;
        while (n > 0) {
            ans = ans * n-- % m;
        }
        return ans;
    }

    //埃氏筛
    int countPrime(int n) {
        int ans = 0;
        int[] prime = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (prime[i] == 0) {
                ans++;
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = 1;
                }
            }
        }
        return ans;
    }

    //线性筛
    int countPrime1(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n ; i++) {
            if (!isPrime[i])
                primes.add(i);
            for (int j = 0; j < primes.size() && primes.get(j) * i <= n; j++) {
                isPrime[primes.get(j) * i] = true;
                if (i % primes.get(j) == 0)
                    break;
            }
        }
        return primes.size();
    }

    public static void main(String[] args) {
        PrimeArrangement p = new PrimeArrangement();
        assert Objects.equals(12, p.numPrimeArrangements(5));
        assert Objects.equals(682289015, p.numPrimeArrangements(100));
    }
}
