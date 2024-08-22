package com.flexport;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static void main(String[] args) {

        Map city = init();

        Car car = new Car("car", 0,0, 2);
        System.out.printf("min:" + city.simulate(car));
    }

    public static Map init(){
        Road A = new Road("A", 2, 2, true);
        Road B = new Road("B", 2, 1, true);
        Road J = new Road("J", 1, 3, false);

        Light AJ = new Light(1,2);
        Light BJ = new Light(1,1);

        Map city = new Map(Arrays.asList(A,B,J), Arrays.asList(AJ, BJ), 2, 3);

        return city;
    }

    /**
     * 道路：水平或垂直
     * 水平：(null, y)
     * 垂直：(x, null)
     */
    static class Road{
        private String name;
        Integer x;
        Integer y;
        Boolean horizontal;

        public Road(String name, Integer x, Integer y, Boolean horizontal) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.horizontal = horizontal;
        }

        public boolean horizontal(){
            return horizontal;
        }
    }

    /**
     * 路灯：坐标 + 颜色
     */
    static class Light{
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
    static class Map{
        List<Road> roads;
        List<Light> lights;

        // 城市地图的边界
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
            java.util.Map<String, Light> lightMap = lights.stream().collect(Collectors.toMap(light -> String.format("(%s,%s)",light.x, light.y), Function.identity()));

            int min = 0;
            // 当车辆的坐标超出边界时结束
            for (; x >= car.x && y >= car.y && car.x >= 0 && car.y >= 0; min++){
                // 先判断当前是否在路口以及路口红绿灯颜色；如果没有红绿灯或者为绿灯，那么车辆直接沿着前进方向向前一步
                Light light = lightMap.get(String.format("(%s,%s)",car.x, car.y));
                if (light == null || light.green){
                    // 行驶方向：1：从西到东；2：从东到西；3：从北到南；4：从南到北
                    switch (car.direction){
                        case 1:
                            car.x += 1;
                            break;
                        case 2:
                            car.x -= 1;
                            break;
                        case 3:
                            car.y += 1;
                            break;
                        case 4:
                            car.y -= 1;
                            break;
                    }
                }

                // 然后切换红绿灯颜色
                lights.forEach(l -> l.toggle());
            }
            return min;
        }
    }

    /**
     * 车辆：名称 + 起点坐标
     */
    static class Car{
        String name;
        Integer x;
        Integer y;
        // 行驶方向：1：从西到东；2：从东到西；3：从北到南；4：从南到北
        Integer direction;

        public Car(String name, Integer x, Integer y, Integer direction) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
