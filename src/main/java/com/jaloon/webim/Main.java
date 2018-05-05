/**
 * Copyright (C), 2017-2018, 厦门普利通信息科技有限公司
 * FileName: Main.java
 * Author:   jalon
 * Date:     2018/5/5 13:14
 * Description: 主类（包含主方法）
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
package com.jaloon.webim;

import com.jaloon.webim.starter.ImWebSocketStarter;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈主类（包含主方法）〉
 *
 * @author jalon
 * @create 2018/5/5 13:14
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ImWebSocketStarter.start();
    }
}