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
@Cache(region="movie", usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "movie")
public class Movie implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long movieId;
	
	@Column(name = "name")
	protected String movieName;

	@Column(name = "url_path")
	protected String urlPath;
	
	@Column(name = "real_path")
	protected String realPath;
	
	@Column(name = "description")
	protected String description;
	
	@Column(name = "detail")
	protected String detail;
	
	@Column(name = "img_url")
	protected String imgUrl;
	
	@Column(name = "img_path")
	protected String imgPath;

	@ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
	protected Category category;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Transient
	protected List<Movie> listRelateMovie;
	
	public List<Movie> loadListRelateMovie(CommonMgr commonMgr) {
		if(listRelateMovie != null) {
			return listRelateMovie;
		}
		
		try {
			listRelateMovie = commonMgr.getListMovies(this.category.getCatId(), this.movieId);
		} catch(Exception e) {
			listRelateMovie = new ArrayList<Movie>();
		}
		
		return listRelateMovie;
	}

	public List<Movie> getListRelateMovie() {
		return listRelateMovie;
	}

	public void setListRelateMovie(List<Movie> listRelateMovie) {
		this.listRelateMovie = listRelateMovie;
	}
}
