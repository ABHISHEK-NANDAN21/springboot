package com.timesheet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.timesheet.model.TimeEntry;

/**
 * @author nandan
 */

public interface TimeentryRepository extends JpaRepository<TimeEntry, Integer> {
	
	

	List<TimeEntry> findByEmpid(String empid);

	@Query("SELECT o from TimeEntry o WHERE o.date BETWEEN :from AND :to AND  o.empid = :empid")
	List<TimeEntry> findAll(@Param("from") Date fromdate, @Param("to") Date todate, @Param("empid") String empid);

	@Query("SELECT o from TimeEntry o WHERE o.date BETWEEN :from AND :to ORDER BY o.date")
	List<TimeEntry> findAll(@Param("from") Date fromdate, @Param("to") Date todate);

	List<TimeEntry> findById(int id);

	List<TimeEntry> findByDate(Date date);

	List<TimeEntry> findByProjectid(String projectid);

}
