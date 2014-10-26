package com.carsFinder.dao;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.activation.DataSource;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DBO {

	private Connection conn;
	private Statement stmt;
  	private DataSource ds;
	
	public DBO()
	{
	}

	
	public void open() 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");           
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/carfinder?zeroDateTimeBehavior=convertToNull","root","root");
			stmt=conn.createStatement();
			System.out.println("Connected Successfully");
		} 
		catch (Exception ex) 
		{
		System.err.println("Connection Error: " + ex.getMessage());
		}
	}

	
	public void close() 
	{
		try 
		{
		
	
			conn.close();
			System.out.println ("Connection Closed");
		} 
		catch (SQLException ex) 
		{
			System.err.println("Errors when close the connection: " + ex.getMessage());
		}
	}


	public ResultSet executeQuery(String sql) throws SQLException
	{
		ResultSet rs = null;
		
                rs=conn.prepareStatement(sql).executeQuery();

		//rs = stmt.executeQuery(sql);
		System.out.println ("Successful Query!");
		return rs;
	}

	
	public int executeUpdate(String sql) throws SQLException
	{
		int ret = 0;
		
	
		ret = stmt.executeUpdate(sql);
	
		System.out.println ("Successful Update!");
		return ret;
	}

	
	public void addBatch(String sql) throws SQLException 
	{
		stmt.addBatch(sql);
	}

	
	public int [] executeBatch() throws SQLException 
	{
		boolean isAuto=conn.getAutoCommit();
		
		conn.setAutoCommit(false);
		int [] updateCounts = stmt.executeBatch();
		

		return updateCounts;
	}
	public boolean getAutoCommit() throws SQLException
	{
		return conn.getAutoCommit();
	}
	public void setAutoCommit(boolean auto)  throws SQLException 
	{
		conn.setAutoCommit(auto);
	}
	
	public void commit() throws SQLException 
	{
		conn.commit();

	}
	public void rollBack() throws SQLException 
	{
		conn.rollback();
//		this.close();
	}
	
}
