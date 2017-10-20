package com.effectivetesting.entities;

import java.util.List;

public class Entry {
	private String id;
	private String title;
	private String slug;
	private String num_results;
	private List<Entry> objects;
	private String page;
	private String total_pages;
	private String author_id;
	private String body;
	private String status;
	private String created_timestamp;
	private String modified_timestamp;
	private List<Tag> tags;
	private List<Comment> comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(String created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public String getModified_timestamp() {
		return modified_timestamp;
	}

	public void setModified_timestamp(String modified_timestamp) {
		this.modified_timestamp = modified_timestamp;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTotal_pages() {
		return total_pages;
	}
	
	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public void setTotal_pages(String total_pages) {
		this.total_pages = total_pages;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List<Entry> getObjects() {
		return objects;
	}
	
	public void setObjects(List<Entry> objects) {
		this.objects = objects;
	}
	
	public String getNum_results() {
		return num_results;
	}
	
	public void setNum_results(String num_results) {
		this.num_results = num_results;
	}

}