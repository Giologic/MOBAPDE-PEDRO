package com.example.jkc.pedrosystem.model;

import com.example.jkc.pedrosystem.util.ImageUtils;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@DatabaseTable(tableName = "Post")
public class Post {
	@DatabaseField(id=true)
	private int id;
	@DatabaseField
	private String contents;
	@DatabaseField
	private String title;
	@DatabaseField
	private Date date;
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "org_id")
	private Organization org;
	@DatabaseField(dataType = DataType.BYTE_ARRAY)
	private byte[] img;
	
	protected Post(){}

	public Post(String contents, String title, Date date) {
		super();
		this.contents = contents;
		this.title = title;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getContents() {
		return contents;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public Organization getOrg() {
		return org;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
	public byte[] getImg(){
		return img;
	}
	
	public Post setImg(File img) throws IOException{
		this.img = ImageUtils.readImageOldWay(img);
		return this;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", contents=" + contents + ", title=" + title
				+ ", date=" + date + ", org=" + org.getName() + "]";
	}
	
	
}
