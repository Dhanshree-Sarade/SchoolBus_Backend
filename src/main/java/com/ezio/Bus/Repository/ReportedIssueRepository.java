package com.ezio.Bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezio.Bus.Entity.ReportedIssue;

public interface ReportedIssueRepository extends JpaRepository<ReportedIssue, Long> {

}
