package com.levkip.fibpet.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.levkip.fibpet.api.model.Fib;

@Repository
public class FibonacciRepository
{
	private JdbcTemplate jdbcTemplate;
	
	public FibonacciRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS fibonacci (`index` int NOT NULL, `value` bigint NOT NULL, PRIMARY KEY (`index`))");
	}
	
	public List<Fib> get() {
		String sql = "SELECT * FROM fibonacci";
		
		try {
    		List<Fib> res = jdbcTemplate.query(sql, new ResultSetExtractor<List<Fib>>(){
    
    			@Override
    			public List<Fib> extractData(ResultSet rs) throws SQLException, DataAccessException
    			{
    				List<Fib> result = new ArrayList<>();
    				while (rs.next()) {
    					result.add(
    						new Fib(rs.getInt("index"), rs.getLong("value"))
						);
    				}
    				return result;
    			}}
    		);
    		
    		return res;
		} catch (EmptyResultDataAccessException e) {
			// ignore
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return Collections.emptyList();
	}
	
	public Fib getOne(Integer index) {
		
		String sql = "SELECT * FROM fibonacci WHERE `index`=? LIMIT 0,1";
		try {
    		return jdbcTemplate.query(sql, new ResultSetExtractor<Fib>(){
    
    			@Override
    			public Fib extractData(ResultSet rs) throws SQLException, DataAccessException
    			{
    				return (rs.next()) ? new Fib(rs.getInt("index"), rs.getLong("value")) : null;	
    			}}
    		, index);
		} catch (EmptyResultDataAccessException e) {
			// ignore
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return null;
		
	}
	
	public boolean save(Integer index, Long value) {
		
		String sql = "INSERT INTO fibonacci VALUES (?, ?)";

		try {
			jdbcTemplate.update(sql, index, value);
			return true;
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}
	
	
	public boolean delete(Integer index) {
		
		String sql = "DELETE FROM fibonacci WHERE `index`=?";
		
		try {
			jdbcTemplate.update(sql, index);
			return true;
		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}

}
