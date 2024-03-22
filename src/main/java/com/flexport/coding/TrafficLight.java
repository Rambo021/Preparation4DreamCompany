package com.flexport.coding;

import java.util.List;

/**
 * @description 红绿灯
 * 构建一个程序模拟车辆在城市道路中通行的情景
 *        J
 *        |
 *   A ---🚦---
 *        |
 *   B ---🚦---
 *        |
 *  如上图所示：一个城市有若干道路，每个十字路口有个红绿灯，假设一辆车从城市内的某个坐标(x,y)出发，在十字路口遇到红灯时停车等待，遇到绿灯时直接通行，请返回该车辆驶出城市时用的时间。
 *  条件：
 *      1. 水平路和垂直路相交形成十字路口，在十字路口设置红绿灯；
 *      2. 车辆从一条道路入口行驶到下一个十字路口需要1分钟；从一个十字路口到下一个十字路口也需要1分钟；
 *      3. 红绿灯每1分钟切换一次颜色；
 *      4. 车辆沿直线行驶，当行驶到道路的终点时表明驶出城市
 *
 *
 *  此题是一个模拟题，需要将现实问题用程序建模来模拟；
 *  因此，需要构建多个类来表示此题中出现的各种实物，如：道路、红绿灯、地图(表示城市的道路组成和边界)、车辆
 */
public class TrafficLight {


    /**
     * 道路：水平或垂直
     * 水平：(null, y)
     * 垂直：(x, null)
     */
    class Road{
        Integer x;
        Integer y;

        public Road(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public boolean Horizontal(){
            return x == null;
        }
    }

    /**
     * 路灯：坐标 + 颜色
     */
    class Light{
        Integer x, y;
        boolean green;

        public Light(Integer x, Integer y) {
            this.x = x;
            this.y = y;
            // 初始化为绿灯
            this.green = true;
        }

        public void toggle(){
            this.green = !green;
        }
    }

    /**
     * 城市地图：定义城市边界和内部道路与十字路口的路灯
     * 默认城市坐标轴原点为(0,0)
     */
    class Map{
        List<Road> roads;
        List<Light> lights;

        Integer x;
        Integer y;

        public Map(List<Road> roads, List<Light> lights, Integer x, Integer y) {
            this.roads = roads;
            this.lights = lights;
            this.x = x;
            this.y = y;
        }

        /**
         * Car从指定坐标沿着指定方向行驶
         * 每次循环更新Car的坐标和红绿灯
         * @param car
         * @return
         */
        public Integer simulate(Car car){

            return 0;
        }
    }

    /**
     * 车辆：名称 + 起点坐标
     */
    class Car{
        String name;
        Integer x;
        Integer y;
        // 行驶方向：1：从西到东；2：从东到西；3：从北到南；4：从南到北
        Integer direction;

        public Car(String name, Integer x, Integer y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}
