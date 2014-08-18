package demo.quartz.job;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClusterDemoJob.
 * 
 * @author
 * 
 */
public class ClusterDemoJob implements Serializable {

	/**
	 * Log
	 */
	private static Log log = LogFactory.getLog(ClusterDemoJob.class);

	/**
	 * Execute.
	 * 
	 * @throws InterruptedException
	 */
	public void execute() throws InterruptedException {
		log.debug("Cluster demo execute begin!");
		Thread.sleep(15000);
		log.debug("Cluster demo execute end!");
	}

}
