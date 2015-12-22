package org.qcis;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FilenameUtils;

public class PDF2DB {

	final static String pdfDirectoryPath = "C:\\Users\\125157\\Documents\\workspace-sts-3.6.4.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\UploadFileServletApp\\pdf"; //the closest layer directory to file
	final static String yamlDirectoryPath = "C:\\Users\\125157\\yaml\\";
	final static String jarDirectoryPath = "C:\\Users\\125157\\myjar\\";
	final static String jarName = "mappdf-0.0.1-SNAPSHOT.jar";
	
//	public static void main(String[] args) throws IOException {
//
//		batchload();
//	}

	public void batchload() throws IOException {
		
		File folder = new File(pdfDirectoryPath);
		File[] filelist = folder.listFiles();
		
		if (filelist != null) {
			
			for (File child : filelist) {
				String filename = child.getName();
				String uid = FilenameUtils.getBaseName(filename);
				
//				System.out.println("This is user " + filename );
				System.out.println("This is user " + uid );
				
				callPdftk(uid);
//				callJar(uid);	
				
			}		
		}
		
//		callSP();
//		deleteFile(pdfDirectoryPath);
//		deleteFile(yamlDirectoryPath);
	}
	
	private void callPdftk(String uid) throws IOException {
		Process process = Runtime.getRuntime().exec(
				"pdftk " + pdfDirectoryPath + uid
						+ ".pdf dump_data_fields output " + yamlDirectoryPath + uid
						+ ".yaml");
		InputStream in = process.getInputStream();
		byte buff[] = new byte[1024];
		int cbRead;
		try {
			while ((cbRead = in.read(buff)) != -1) {
				// Use the output of the process...
			}

		} catch (IOException e) {
				e.getStackTrace();
		}
		// No more output was available from the process, so...
		
		// Ensure that the process completes
		
		//Check if process terminated
		//System.out.println("exit value is " + process.exitValue());
		
		// Then examine the process exit code
		if (process.exitValue() == 0) {
			System.out.println("pdf to yaml done: " + uid);
		}		
	}

	private void callJar(String uid) throws IOException {
		Process process = Runtime.getRuntime().exec(
				"java -jar " + jarDirectoryPath 
						+ jarName + "--input.file="
						+ yamlDirectoryPath + uid + ".yaml  --uid=" + uid);
		
		InputStream in = process.getInputStream();
		byte buff[] = new byte[1024];
		int cbRead;
		try {
			while ((cbRead = in.read(buff)) != -1) {
				// Use the output of the process...
			}

		} catch (IOException e) {
			e.getStackTrace();
		}
		// No more output was available from the process, so...
		
		//Check if process terminated
		//System.out.println("exit value is " + process.exitValue());
				
		// Then examine the process exit code
				if (process.exitValue() == 0) {
					System.out.println("yaml to database done: " + uid);
				}
		
	}
	
	private void deleteFile(String path) throws IOException {
		
		File folder = new File(path);
		File[] filelist = folder.listFiles();
		if (filelist != null) {
			for (File child : filelist) {
				child.delete();
				System.out.println(child.getName() + " is deleted");
			}
		}

	}

	private void callSP() {
		try {

			// java.sql.PreparedStatement sta = connectmysql().
			// prepareStatement("select count(*) as num from travel_info where id > 114");
			// ResultSet rs = sta.executeQuery();
			// rs.next();
			// int flag = rs.getInt("num");
			// System.out.println("travel id rows are:" +flag);

			String sql = "call sp_staging";
			CallableStatement cs = connectmysql().prepareCall(sql);
			cs.execute();
			connectmysql().close();
			
			System.out.println("calling SP done");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection connectmysql() throws SQLException,
			ClassNotFoundException {

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://138.25.213.226:3306/qcis_admin";
		String username = "gong";
		String password = "gong";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}
