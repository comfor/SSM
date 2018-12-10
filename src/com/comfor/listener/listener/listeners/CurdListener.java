package com.comfor.listener.listener.listeners;

import com.comfor.listener.listener.events.ICurdEvent;

// 定义监听器类
public class CurdListener implements IListener {

	@Override
	public void handle(ICurdEvent event) {
		String eventType = event.getEventType();
		if(ICurdEvent.CRE_EVENT.equals(eventType)) {          // 若事件类型为“添加”
			System.out.println("事件源执行了 添加 操作");
		} else if(ICurdEvent.DEL_EVENT.equals(eventType)) {   // 若事件类型为“删除”
			System.out.println("事件源执行了 删除 操作");
		} else if(ICurdEvent.UPD_EVENT.equals(eventType)) {   // 若事件类型为“修改”
			System.out.println("事件源执行了 修改 操作");
		} else if(ICurdEvent.RET_EVENT.equals(eventType)) {   // 若事件类型为“查询”
			System.out.println("事件源执行了 查询 操作");
		}
	}

}
