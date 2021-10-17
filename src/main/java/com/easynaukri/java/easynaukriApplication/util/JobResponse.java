package com.easynaukri.java.easynaukriApplication.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.easynaukri.java.easynaukriApplication.model.Job;

public class JobResponse {
    private String message;
    private String error;
    private List<Job> jobs;
    private Page<?> pagesJob;
    private Job job;
    public JobResponse(final String message) {
        super();
        this.message = message;
    }

    public JobResponse(String message, String error, List<Job> jobs) {
		super();
		this.message = message;
		this.error = error;
		this.jobs = jobs;
	}
    
    public JobResponse(String message, String error, Page<?> jobs) {
		super();
		this.message = message;
		this.error = error;
		this.pagesJob = jobs;
	}

	public JobResponse(final String message, final String error) {
        super();
        this.message = message;
        this.error = error;
    }
	
	public JobResponse(final String message, final String error,Job job) {
        super();
        this.message = message;
        this.error = error;
        this.job=job;
    }

    public JobResponse(List<ObjectError> allErrors, String error) {
        this.error = error;
        String temp = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            } else {
                return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            }
        }).collect(Collectors.joining(","));
        this.message = "[" + temp + "]";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Page<?> getPagesJob() {
		return pagesJob;
	}

	public void setPagesJob(Page<Job> pagesJob) {
		this.pagesJob = pagesJob;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
