package com.timesheet.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.timesheet.model.TimeEntry;
import com.timesheet.repository.TimeentryRepository;

@RestController
@RequestMapping(value = "/timeentry")
public class Controller {
	/**
	 * A Rest Controller is an extension of the Base Controller which has
	 * RESTful support built in. This will allow you to build API's with ease.
	 * controller method "findAll" is called by the following URL:
	 * http://localhost:8080/TimeEntry/findAll controller method "addTimeEntry"
	 * is called by the following URL:
	 * http://localhost:8080/timeentry/addtimeentry controller method "findById"
	 * is called by the following URL: http://localhost:8080/timeentry/getbyid
	 * controller method "findByEmpid" is called by the following URL:
	 * http://localhost:8080/timeentry/getbyempid controller method
	 * "findByProjectid" is called by the following URL:
	 * http://localhost:8080/timeentry/getbyprojectid controller method
	 * "findByDateRange" is called by the following URL:
	 * http://localhost:8080/timeentry/findbydateRange controller method
	 * "findByDate" is called by the following URL:
	 * http://localhost:8080/timeentry/findbydate controller method
	 * "findByDateRange" is over loaded and is called by the following URL:
	 * http://localhost:8080/timeentry/findbydateRangeandempid
	 * 
	 * @RestController is used to implement a RESTful web services, the response
	 *                 would be always sent with the response body.
	 * @RequestMapping is used to obtain the mapping at any particular url
	 * @RequestParam is used to obtain an parameter.
	 */

	@Autowired
	TimeentryRepository repository;

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public List<TimeEntry> findAll() {
		return repository.findAll();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addtimeentry")
	public void addTimeEntry(@RequestBody TimeEntry entry) {
		repository.save(entry);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getbyid")
	public List<TimeEntry> getDetails(@RequestParam(defaultValue = "id") int id) {

		List<TimeEntry> details = repository.findById(id);

		return details;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getbyempid")
	public List<TimeEntry> getDetails(@RequestParam(value = "empid") String empid) {

		List<TimeEntry> details = repository.findByEmpid(empid);

		return details;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getbyprojectid")
	public List<TimeEntry> getDetails2(@RequestParam(value = "projectid") String projectid) {

		List<TimeEntry> details = repository.findByProjectid(projectid);

		return details;

	}

	@RequestMapping(value = "/findbydateRange")
	public List<TimeEntry> findByDateRange(@RequestParam("fromdate") String f_date,
			@RequestParam("todate") String t_date) throws ParseException {

		Date fromdate = new SimpleDateFormat("ddMMyyyy").parse(f_date);
		Date todate = new SimpleDateFormat("ddMMyyyy").parse(t_date);
		return repository.findAll(fromdate, todate);
	}

	@RequestMapping(value = "/findbydate")
	public @ResponseBody List<TimeEntry> findByDate(
			@RequestParam("date") @DateTimeFormat(pattern = "ddMMyyyy") Date date) throws ParseException {

		// Date date1 = new SimpleDateFormat("ddMMyyyy").parse(date);
		return repository.findByDate(date);
	}

	@RequestMapping(value = "/findbydateRangeandempid")
	public List<TimeEntry> findByDateRange(@RequestParam("fromdate") String f_date,
			@RequestParam("todate") String t_date, @RequestParam("empid") String empid) throws ParseException {

		Date fromdate = new SimpleDateFormat("ddMMyyyy").parse(f_date);
		Date todate = new SimpleDateFormat("ddMMyyyy").parse(t_date);
		return repository.findAll(fromdate, todate, empid);
	}

}
