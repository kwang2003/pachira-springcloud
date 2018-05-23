package com.pachiraframework.scheduler.entity;

import java.util.Date;

import com.pachiraframework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 任务执行历史记录
 * @author kevin
 *
 */
@Getter
@Setter
@ToString(callSuper=true)
public class JobHistory extends BaseEntity<Long> {
	private static final long serialVersionUID = 3492100091826780726L;
	private Long jobId;
	private String jobName;
	private String cron;
	/**
	 * 任务开始执行时间
	 */
	private Date startedAt;
	/**
	 * 任务结束时间
	 */
	private Date endedAt;
	/**
	 * 任务执行的结果消息（可能是异常）
	 */
	private String message;
	private String status;
	private String instance;
	
	@AllArgsConstructor
	public static enum StatusEnum{
		/**
		 * 进行中
		 */
		ING,
		/**
		 * 成功
		 */
		SUCCESS,
		/**
		 * 失败
		 */
		FAIL;
	}
}
