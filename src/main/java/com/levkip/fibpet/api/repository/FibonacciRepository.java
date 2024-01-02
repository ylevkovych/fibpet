package com.levkip.fibpet.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class FibonacciRepository
{
	private JdbcTemplate jdbcTemplate;
	
	public FibonacciRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS fibonacci (`index` int NOT NULL, `value` bigint NOT NULL, PRIMARY KEY (`index`))");
	}
	
	public List<Map<Integer, Long>> get() {
		String sql = "SELECT * FROM fibonacci WHERE `index`= ?";
		try {
    		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Map<Integer, Long>>>(){
    
    			@Override
    			public List<Map<Integer, Long>> extractData(ResultSet rs) throws SQLException, DataAccessException
    			{
    				List<Map<Integer, Long>> result = new ArrayList<>();
    				while (rs.next()) {
    					Map<Integer, Long> map = new HashMap<>();
    					map.put(rs.getInt("index"), rs.getLong("value"));
    				}
    				return result;
    			}}
    		);
		} catch (EmptyResultDataAccessException e) {
			// ignore
		} catch (DataAccessException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return null;
	}
	
	public Map<Integer, Long> getOne(Integer index) {
		
		String sql = "SELECT * FROM fibonacci WHERE `index`=? LIMIT 0,1";
		try {
    		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<Integer, Long>>(){
    
    			@Override
    			public Map<Integer, Long> extractData(ResultSet rs) throws SQLException, DataAccessException
    			{
    				Map<Integer, Long> result = new HashMap<>();
    				if (rs.next()) {
    					result.put(rs.getInt("index"), rs.getLong("value"));
    				}
    				return result;
    			}}
    		, index);
		} catch (EmptyResultDataAccessException e) {
			// ignore
		} catch (DataAccessException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		return null;
		
	}
	
	public boolean save(Integer index, Long value) {
		
		String sql = "INSERT INTO fibonacci (`index`, `value`) values (?, ?) ON DUPLICATE KEY UPDATE `value`=?";
		
		try {
			jdbcTemplate.update(sql, index, value, value);
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
