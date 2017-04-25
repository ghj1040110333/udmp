package cn.com.git.udmp.test.druid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Spring Cao
 *
 */
public class MultiThreadTest {

	public static void main(String a[]) throws Exception {
		testByMultiThread(DataSourceUtil.DRUID_MYSQL_SOURCE, 50);
	}

	public static void testByMultiThread(int dbType, int times) throws Exception {
		//set Thread number from System Available Processors
		int numOfThreads = Runtime.getRuntime().availableProcessors() * 2;
		//build up a MultiThread Executor
		ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
		final TableOperator test = new TableOperator();
		//set Druid_MySQL Arguments
		test.setDataSource(DataSourceUtil.getDataSource(dbType));
		//continuous work flag
		boolean createResult = false;
		try {
			//create a test table
			test.createTable();
			createResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (createResult) {
			List<Future<Long>> results = new ArrayList<Future<Long>>();
			for (int i = 0; i < times; i++) {
				//base on java Concurrent Future callback implementor
				results.add(executor.submit(new Worker(test)));
			}
			executor.shutdown();
			while (!executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS));

			long sum = 0;
			for (Future<Long> result : results) {
				sum += result.get();
			}

			System.out.println("---------------db type " + dbType + "------------------");
			System.out.println("number of threads :" + numOfThreads + " times:"	+ times);
			System.out.println("running time: " + sum + "ms");
			System.out.println("TPS: " + (double) (200 * 1000) / (double) (sum));
			System.out.println();
			try {
				 test.tearDown();
				// dropResult = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Initialization Database Failure !");
		}
	}
}
