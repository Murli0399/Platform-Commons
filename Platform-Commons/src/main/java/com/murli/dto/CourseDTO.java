package com.murli.dto;

import java.util.List;

public class CourseDTO {
	private String courseName;
	private String description;
	private String courseType;
	private int duration;
	private List<String> topics;

	public CourseDTO() {
		super();
	}

	public CourseDTO(String courseName, String description, String courseType, int duration, List<String> topics) {
		super();
		this.courseName = courseName;
		this.description = description;
		this.courseType = courseType;
		this.duration = duration;
		this.topics = topics;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

}
