package com.flexport.coding;


import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 写一段java代码,有一组版本号如下["0.1.1","2.3.3","0.302.1","4.2","4.3.5","4.3.4.5"]。  999
 * 现在需要对其进行排序,排序的结果为["4.3.5","4.3.4.5","4.2","2.3.3","0.302.1","0.1.1"]
 */
public class XTransfer {

    public static void main(String[] args) {
        /*String a = "4.3.5", b= "4.3.4.5";

        System.out.printf("" + compare(a, b));*/

        String[] versionAra = {"0.1.1","2.3.3","0.302.1","4.2","4.3.5","4.3.4.5"};
        // 排序
        Arrays.sort(versionAra, (o1, o2) -> compareVer(o1, o2));

        String result = Arrays.asList(versionAra).stream().collect(Collectors.joining(", "));

        System.out.printf(result);
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    public static int compareVer(String a, String b){
        String[] aAry = a.split("\\.");
        String[] bAry = b.split("\\.");

        for (int i = 0; i < aAry.length || i < bAry.length; i++) {
            // 越界的情况下,判断a和b的长度
            if (i >= aAry.length || i >= bAry.length){
                return aAry.length - bAry.length;
            }

            // 非越界的情况,判断数字
            Integer aI = Integer.valueOf(aAry[i]);
            Integer bI = Integer.valueOf(bAry[i]);

//            System.out.println("aI:" + aI + "bI:" + bI);

            return aI.compareTo(bI);
            /*if (aI.compareTo(bI) > 0){
                return true;
            }else if (aI.compareTo(bI) < 0){
                return false;
            }*/
        }

        return -1;
    }

    /**
     * 核心功能：
     * 1. 银行卡充值；
     * 2. 账户相互转账；
     * 3. 转账到银行卡：
     * 非核心功能：
     * 1. 用户注册、登陆；
     * 2. 注销、登出
     *
     * 日活：数百万，300万；
     * 读：
     * 平均QPS：3000000 / 24 / 60 / 60 = 34.7 * 100 = 3470;
     * 高峰QPS：3000000 / 12 / 60 / 60 = 69.4== 70 * 100 = 7000;
     *
     * 写：
     *   平均：700；
     *   高峰；1400；
     *
     * 数据库：多住多从的架构
     *   1. 读写分离；提高读数据的吞吐量；
     *   2. 数据库拆分：写核心数据和非核心数据分离；
     *      a. 用户模块： 用户信息、用户银行卡
     *      b. 钱包账户；
     *  缓存：
     *     1. 缓存用户信息、银行卡列表等
     *
     *  功能：
     *     1. 银行卡充值
     *          a. 与第三方交互：无法使用分布式事务改造。
     *          b. 与银行API交互，成功后调用钱包服务修改金额；
     *     2. 转账到银行卡
     *          a. 调用钱包服务修改可用余额和不可以余额；
     *          b. 与银行API交互，等待结果(同步或异步)；
     *          c. 根据银行API结果发送消息到MQ，由钱包服务消费进行业务处理；
     *     3. 账户相互转账
     *         a. 本地事务处理；
     *
     */
}
