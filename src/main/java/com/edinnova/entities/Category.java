package com.edinnova.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.edinnova.services.CommonMgr;

@Entity
@Cache(region="category", usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "category")
public class Category implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long catId;
	
	@Column(name = "name")
	protected String catName;

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	@Transient
	protected List<Movie> listMovie;
	
	public List<Movie> loadListMoview(CommonMgr commonMgr) {
		if(listMovie != null) {
			return listMovie;
		}
		
		try {
			listMovie = commonMgr.getListMovies(this.catId, null);
		} catch(Exception e) {
			listMovie = new ArrayList<Movie>();
		}
		
		return listMovie;
	}

	public List<Movie> getListMovie() {
		return listMovie;
	}

	public void setListMovie(List<Movie> listMovie) {
		this.listMovie = listMovie;
	}
}
