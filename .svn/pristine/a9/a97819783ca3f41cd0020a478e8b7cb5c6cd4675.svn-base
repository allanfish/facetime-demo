package com.conlect.oatos.utils;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 支持多任务的秒表实现<br>
 */
public class StopWatch {

	private final String _id;
	private boolean _keepTasks = true;
	private long _startTimeMillis;
	private long _totalTimeMillis;
	private boolean _running;
	private final List<TaskInfo> _taskList = new LinkedList<TaskInfo>();
	private String _currentTaskName;
	private TaskInfo _lastTaskInfo;
	private int _taskCount;

	public StopWatch() {
		_id = "";
	}

	public StopWatch(String id) {
		_id = id;
	}

	public long getLastTaskTimeMillis() throws IllegalStateException {
		if (_lastTaskInfo == null)
			throw new IllegalStateException(
					"No tests run: can't get last interval");
		return _lastTaskInfo.getTimeMillis();
	}

	public int getTaskCount() {
		return _taskCount;
	}

	public TaskInfo[] getTaskInfo() {
		if (!_keepTasks)
			throw new UnsupportedOperationException(
					"Task info is not being kept!");
		return _taskList.toArray(new TaskInfo[_taskList.size()]);
	}

	public long getTotalTimeMillis() {
		return _totalTimeMillis;
	}

	public double getTotalTimeSeconds() {
		return _totalTimeMillis / 1000.0;
	}

	public boolean isRunning() {
		return _running;
	}

	public String prettyPrint() {
		StringBuilder sb = new StringBuilder(shortSummary());
		sb.append('\n');
		if (!_keepTasks)
			sb.append("No task info kept");
		else {
			TaskInfo[] tasks = getTaskInfo();
			sb.append("-----------------------------------------\n");
			sb.append("ms     %     Task getName\n");
			sb.append("-----------------------------------------\n");
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMinimumIntegerDigits(5);
			nf.setGroupingUsed(false);
			NumberFormat pf = NumberFormat.getPercentInstance();
			pf.setMinimumIntegerDigits(3);
			pf.setGroupingUsed(false);
			for (TaskInfo task : tasks) {
				sb.append(nf.format(task.getTimeMillis()) + "  ");
				sb.append(pf.format(task.getTimeSeconds()
						/ getTotalTimeSeconds())
						+ "  ");
				sb.append(task.getTaskName() + "\n");
			}
		}
		return sb.toString();
	}

	public void setKeepTaskList(boolean keepTaskList) {
		_keepTasks = keepTaskList;
	}

	/**
	 * Return a short description of the total running time.
	 */
	public String shortSummary() {
		return "Stopwatch '" + _id + "': running time (millis) = "
				+ getTotalTimeMillis();
	}

	/**
	 * 启动一个给定名称的任务
	 */
	public void start(String taskName) throws IllegalStateException {
		if (_running)
			throw new IllegalStateException(
					"Can't start StopWatch: it's already running");
		_startTimeMillis = System.currentTimeMillis();
		_currentTaskName = taskName;
		_running = true;
	}

	/**
	 * 停止当前任务，接着可以开始新的任务
	 */
	public void stop() throws IllegalStateException {
		if (!_running)
			throw new IllegalStateException(
					"Can't stop StopWatch: it's not running");
		long lastTime = System.currentTimeMillis() - _startTimeMillis;
		_totalTimeMillis += lastTime;
		_lastTaskInfo = new TaskInfo(_currentTaskName, lastTime);
		if (_keepTasks)
			_taskList.add(_lastTaskInfo);
		++_taskCount;
		_running = false;
		_currentTaskName = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(shortSummary());
		if (_keepTasks) {
			TaskInfo[] tasks = getTaskInfo();
			for (TaskInfo task : tasks) {
				sb.append("; [" + task.getTaskName() + "] took "
						+ task.getTimeMillis());
				long percent = Math.round(100.0 * task.getTimeSeconds()
						/ getTotalTimeSeconds());
				sb.append(" = " + percent + "%");
			}
		} else
			sb.append("; no task info kept");
		return sb.toString();
	}

	public static class TaskInfo {

		private final String _name;
		private final long _timeMillis;

		private TaskInfo(String taskName, long timeMillis) {
			_name = taskName;
			_timeMillis = timeMillis;
		}

		public String getTaskName() {
			return _name;
		}

		public long getTimeMillis() {
			return _timeMillis;
		}

		public double getTimeSeconds() {
			return _timeMillis / 1000.0;
		}
	}
}
