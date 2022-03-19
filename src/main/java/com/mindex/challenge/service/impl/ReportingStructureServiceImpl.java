package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reporting structure for employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure(employee);
        int numberOfReports = calcNumberOfReports(employee);
        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;
    }

    /*
    method calcNumberOfReports is a recursive function that will calculate the number of direct reports of each employee
    */
    private int calcNumberOfReports(Employee employee) {
        int numberOfReports = 0;
        List<Employee> employeeDirectReports = employee.getDirectReports();

        if (employeeDirectReports == null || employeeDirectReports.isEmpty()) {
            return numberOfReports;
        }
        for (Employee e : employeeDirectReports) {
            Employee reportEmployee = employeeRepository.findByEmployeeId(e.getEmployeeId());
            numberOfReports++;
            numberOfReports += calcNumberOfReports(reportEmployee);
        }
        return numberOfReports;
    }

}
