package com.touch.utils;
import cn.hutool.core.util.NetUtil;

import javax.swing.*;
public class ActiveMQUtil {
	public static void main(String[] args) {
		checkServer();
	}
	public static void checkServer() {
		if(NetUtil.isUsableLocalPort(8161)) {
			JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
			System.exit(1);

		}
	}
}
