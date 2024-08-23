package com.booking.oa.dfs;

/**
 * @author ooo
 * @description 货币转换，构建图，DFS
 *
 * https://leetcode.com/discuss/interview-experience/2411840/Interview-experience-Reject
 *
 * You had given currency conversion table like below
 * EUR = 1.5 GBP
 * GBP = 1.1 USD
 * CAD = 0.85 AED
 * GBP = 100 MXN
 *
 * now let's say user is trying to book hotel, hotel accepts EUR and user's currency USD, you need to show prices in USD for that hotel.
 * along with that each conversion attract some extra txn fees, if conversion not possible return -1.
 * Ex 1. Hotel price = 1000 EUR
 * User currency =USD
 * price shown to user =1650 (approx)+ 2* txn fees.
 * Ex 2. Hotel Price = 100 CAD
 * User currency = USD
 * answer =-1
 *
 * @date 2024/8/23 09:27:43
 */
public class CurrencyConversion {
}
