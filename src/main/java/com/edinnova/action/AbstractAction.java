package com.edinnova.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.edinnova.entities.Category;
import com.edinnova.entities.Movie;
import com.edinnova.services.CommonMgr;
import com.edinnova.util.Configuration;
import com.edinnova.util.Constant;
import com.edinnova.util.DateUtil;
import com.edinnova.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class AbstractAction extends ActionSupport implements Preparable, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7474036730262294151L;

	private static Logger logger = Logger.getLogger(AbstractAction.class);
	
	private Map<String, Object> session;
	
	@Autowired
	protected CommonMgr commonMgr;
	
	private String username;
	
	private String password;
	
	private String errMsg;
	
	private List<Category> listCat;
	
	private List<Category> listCatMenu;
	
	private List<Movie> listMovie;
	
	private String categoryName;
	
	private Long id;
	
	private String submitType;
	
	private String movieName;
	
	private Long catId;
	
	private String description;
	
	private String detail;
	
	private File imageFeature;
	private String imageFeatureFileName;
	private String imageFeatureContentType;
	
	private File movieFile;
	private String movieFileFileName;
	private String movieFileContentType;
	
	private File myFileUpload;
	private String myFileUploadFileName;
	private String myFileUploadContentType;
	private Movie movie;
	
	public void prepare() throws Exception {
		
	}
	
	public String home() throws Exception {
		if(catId != null) {
			Category category = commonMgr.getEntity(Category.class, catId);
			if(category != null) {
				category.loadListMoview(commonMgr);
				listCat = new ArrayList<Category>();
				listCat.add(category);
			}
		} else {
			listCat = commonMgr.getListCategory();
			for(Category cat: listCat) {
				cat.loadListMoview(commonMgr);
			}
		}
		listCatMenu = commonMgr.getListCategory();
		return SUCCESS;
	}
	
	public String login() {
		if(StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password)) {
			return "login";
		}
		
		if(!username.equalsIgnoreCase(Configuration.getAdmin()) || !password.equals(Configuration.getPassword())) {
			errMsg = getText("invalid.username.and.password.message");
			return "login";
		}
		
		session.put("admin", username);
		
		return SUCCESS;
	}
	
	public String admin() throws Exception {
		if(session.get("admin") == null) {
			return "login";
		}
		
		if (!StringUtil.isNullOrEmpty(submitType) && submitType.equals(getText("add.category"))) {
			if(!StringUtil.isNullOrEmpty(categoryName)) {
				Category category = commonMgr.getCategoryByName(categoryName);
				if(category == null) {
					category = new Category();
					category.setCatName(categoryName);
					commonMgr.createEntity(category);
				}
			} else {
				errMsg = getText("error.category.name.is.empty");
			}
		} else if(!StringUtil.isNullOrEmpty(submitType) && submitType.equals(getText("add.video"))) {
			if(!StringUtil.isNullOrEmpty(movieName) && catId != null && !StringUtil.isNullOrEmpty(description) && !StringUtil.isNullOrEmpty(detail) && imageFeature != null && movieFile != null) {
				if(!imageFeatureContentType.equalsIgnoreCase("image/jpeg") //.jpg
						&& !imageFeatureContentType.equalsIgnoreCase("image/pjpeg") //.jpg
						&& !imageFeatureContentType.equalsIgnoreCase("image/bmp") //.bmp
						&& !imageFeatureContentType.equalsIgnoreCase("image/x-windows-bmp") //.bmp
						&& !imageFeatureContentType.equalsIgnoreCase("image/png") //.png
						&& !imageFeatureContentType.equalsIgnoreCase("image/gif")) {//.gif
					errMsg = getText("image.feature.extenstion.is.not.support");
				}
				
				if(!movieFileContentType.equalsIgnoreCase("video/mpeg")//.mpeg
						&& !movieFileContentType.equalsIgnoreCase("application/x-troff-msvideo")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/avi")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/msvideo")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/x-msvideo")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/quicktime")//.mov
						&& !movieFileContentType.equalsIgnoreCase("video/mp4")//.mp4
						&& !movieFileContentType.equalsIgnoreCase("video/x-flv")//flv
						&& !movieFileContentType.equalsIgnoreCase("video/x-ms-wmv")) {//.wmv
					errMsg = getText("image.feature.extenstion.is.not.support");
				}
				
				Category category = commonMgr.getEntity(Category.class, catId);
				
				if(category == null) {
					errMsg = getText("category.does.not.exists");
				}
				
				if(StringUtil.isNullOrEmpty(errMsg)) {
					String postFixString = DateUtil.getPostfixString(); 
					String imgFileName = postFixString.trim();
					if(imageFeatureContentType.equalsIgnoreCase("image/jpeg") || imageFeatureContentType.equalsIgnoreCase("image/pjpeg")) {
						imgFileName = imgFileName + Constant.EXTENSION_JPG;
						//.jpg
					} else if(imageFeatureContentType.equalsIgnoreCase("image/bmp") || imageFeatureContentType.equalsIgnoreCase("image/x-windows-bmp")) {
						imgFileName = imgFileName + Constant.EXTENSION_BMP;
						//.bmp
					} else if(imageFeatureContentType.equalsIgnoreCase("image/png")) {
						imgFileName = imgFileName + Constant.EXTENSION_PNG;
						//.png
					} else if(imageFeatureContentType.equalsIgnoreCase("image/gif")) {
						imgFileName = imgFileName + Constant.EXTENSION_GIF;
						//.gif
					}
					
					String imgRealPath = Configuration.getMovieRealPath() + imgFileName;
					String imgUrlPath = Configuration.getMovieUrlPath() + imgFileName;
					FileUtils.moveFile(imageFeature, new File(imgRealPath));
					
					String videoFileName = postFixString.trim();
					if(movieFileContentType.equalsIgnoreCase("video/mpeg")) {
						videoFileName = videoFileName + Constant.EXTENSION_MPEG;
						//.mpeg
					} else if(movieFileContentType.equalsIgnoreCase("application/x-troff-msvideo") || movieFileContentType.equalsIgnoreCase("video/avi") || movieFileContentType.equalsIgnoreCase("video/msvideo") || movieFileContentType.equalsIgnoreCase("video/x-msvideo")) {
						videoFileName = videoFileName + Constant.EXTENSION_AVI;
						//.avi
					} else if(movieFileContentType.equalsIgnoreCase("video/quicktime")) {
						videoFileName = videoFileName + Constant.EXTENSION_MOV;
						//.mov
					} else if(movieFileContentType.equalsIgnoreCase("video/mp4")) {
						videoFileName = videoFileName + Constant.EXTENSION_MP4;
						//.mp4
					} else if(movieFileContentType.equalsIgnoreCase("video/x-flv")) {
						videoFileName = videoFileName + Constant.EXTENSION_FLV;
						//.flv
					} else if(movieFileContentType.equalsIgnoreCase("video/x-ms-wmv")) {
						videoFileName = videoFileName + Constant.EXTENSION_WMV;
						//.wmv
					}
					
					String videoRealPath = Configuration.getMovieRealPath() + videoFileName;
					String videoUrlPath = Configuration.getMovieUrlPath() + videoFileName;
					FileUtils.moveFile(movieFile, new File(videoRealPath));
					
					Movie movie = new Movie();
					movie.setCategory(category);
					movie.setDescription(description);
					movie.setDetail(detail);
					movie.setImgUrl(imgUrlPath);
					movie.setImgPath(imgRealPath);
					movie.setRealPath(videoRealPath);
					movie.setUrlPath(videoUrlPath);
					movie.setMovieName(movieName);
					
					commonMgr.createEntity(movie);
				}
			} else if(StringUtil.isNullOrEmpty(movieName)) {
				errMsg = getText("movie.name.can.not.empty");
			} else if(catId != null) {
				errMsg = getText("movie.category.can.not.empty");
			} else if(StringUtil.isNullOrEmpty(description)) {
				errMsg = getText("movie.description.can.not.empty");
			} else if(StringUtil.isNullOrEmpty(detail)) {
				errMsg = getText("movie.detail.can.not.empty");
			} else if(imageFeature != null) {
				errMsg = getText("movie.image.feature.can.not.empty");
			} else if(movieFile != null) {
				errMsg = getText("movie.file.can.not.empty");
			}
		}
		
		listCat = commonMgr.getListCategory();
		
		listMovie = commonMgr.getListMovies(null, null);
		
		return SUCCESS;
	}
	
	public String category() throws Exception {
		if(session.get("admin") == null) {
			return "login";
		}
		
		if (!StringUtil.isNullOrEmpty(submitType) && submitType.equals(getText("add.category"))) {
			if(!StringUtil.isNullOrEmpty(categoryName)) {
				Category category = commonMgr.getCategoryByName(categoryName);
				if(category == null) {
					category = new Category();
					category.setCatName(categoryName);
					commonMgr.createEntity(category);
				}
			} else {
				errMsg = getText("error.category.name.is.empty");
			}
		}
		
		listCat = commonMgr.getListCategory();
		
		return SUCCESS;
	}
	
	public String video() throws Exception {
		if(session.get("admin") == null) {
			return "login";
		}
		
		if(!StringUtil.isNullOrEmpty(submitType) && submitType.equals(getText("add.video"))) {
			if(!StringUtil.isNullOrEmpty(movieName) && catId != null && !StringUtil.isNullOrEmpty(description) && !StringUtil.isNullOrEmpty(detail) && imageFeature != null && movieFile != null) {
				if(!imageFeatureContentType.equalsIgnoreCase("image/jpeg") //.jpg
						&& !imageFeatureContentType.equalsIgnoreCase("image/pjpeg") //.jpg
						&& !imageFeatureContentType.equalsIgnoreCase("image/bmp") //.bmp
						&& !imageFeatureContentType.equalsIgnoreCase("image/x-windows-bmp") //.bmp
						&& !imageFeatureContentType.equalsIgnoreCase("image/png") //.png
						&& !imageFeatureContentType.equalsIgnoreCase("image/gif")) {//.gif
					errMsg = getText("image.feature.extenstion.is.not.support");
				}
				
				if(!movieFileContentType.equalsIgnoreCase("video/mpeg")//.mpeg
						&& !movieFileContentType.equalsIgnoreCase("application/x-troff-msvideo")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/avi")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/msvideo")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/x-msvideo")//.avi
						&& !movieFileContentType.equalsIgnoreCase("video/quicktime")//.mov
						&& !movieFileContentType.equalsIgnoreCase("video/mp4")//.mp4
						&& !movieFileContentType.equalsIgnoreCase("video/x-flv")//flv
						&& !movieFileContentType.equalsIgnoreCase("video/x-ms-wmv")) {//.wmv
					errMsg = getText("image.feature.extenstion.is.not.support");
				}
				
				Category category = commonMgr.getEntity(Category.class, catId);
				
				if(category == null) {
					errMsg = getText("category.does.not.exists");
				}
				
				if(StringUtil.isNullOrEmpty(errMsg)) {
					String postFixString = DateUtil.getPostfixString(); 
					String imgFileName = postFixString.trim();
					if(imageFeatureContentType.equalsIgnoreCase("image/jpeg") || imageFeatureContentType.equalsIgnoreCase("image/pjpeg")) {
						imgFileName = imgFileName + Constant.EXTENSION_JPG;
						//.jpg
					} else if(imageFeatureContentType.equalsIgnoreCase("image/bmp") || imageFeatureContentType.equalsIgnoreCase("image/x-windows-bmp")) {
						imgFileName = imgFileName + Constant.EXTENSION_BMP;
						//.bmp
					} else if(imageFeatureContentType.equalsIgnoreCase("image/png")) {
						imgFileName = imgFileName + Constant.EXTENSION_PNG;
						//.png
					} else if(imageFeatureContentType.equalsIgnoreCase("image/gif")) {
						imgFileName = imgFileName + Constant.EXTENSION_GIF;
						//.gif
					}
					
					String imgRealPath = Configuration.getMovieRealPath() + imgFileName;
					String imgUrlPath = Configuration.getMovieUrlPath() + imgFileName;
					FileUtils.moveFile(imageFeature, new File(imgRealPath));
					
					String videoFileName = postFixString.trim();
					if(movieFileContentType.equalsIgnoreCase("video/mpeg")) {
						videoFileName = videoFileName + Constant.EXTENSION_MPEG;
						//.mpeg
					} else if(movieFileContentType.equalsIgnoreCase("application/x-troff-msvideo") || movieFileContentType.equalsIgnoreCase("video/avi") || movieFileContentType.equalsIgnoreCase("video/msvideo") || movieFileContentType.equalsIgnoreCase("video/x-msvideo")) {
						videoFileName = videoFileName + Constant.EXTENSION_AVI;
						//.avi
					} else if(movieFileContentType.equalsIgnoreCase("video/quicktime")) {
						videoFileName = videoFileName + Constant.EXTENSION_MOV;
						//.mov
					} else if(movieFileContentType.equalsIgnoreCase("video/mp4")) {
						videoFileName = videoFileName + Constant.EXTENSION_MP4;
						//.mp4
					} else if(movieFileContentType.equalsIgnoreCase("video/x-flv")) {
						videoFileName = videoFileName + Constant.EXTENSION_FLV;
						//.flv
					} else if(movieFileContentType.equalsIgnoreCase("video/x-ms-wmv")) {
						videoFileName = videoFileName + Constant.EXTENSION_WMV;
						//.wmv
					}
					
					String videoRealPath = Configuration.getMovieRealPath() + videoFileName;
					String videoUrlPath = Configuration.getMovieUrlPath() + videoFileName;
					FileUtils.moveFile(movieFile, new File(videoRealPath));
					
					Movie movie = new Movie();
					movie.setCategory(category);
					movie.setDescription(description);
					movie.setDetail(detail);
					movie.setImgUrl(imgUrlPath);
					movie.setImgPath(imgRealPath);
					movie.setRealPath(videoRealPath);
					movie.setUrlPath(videoUrlPath);
					movie.setMovieName(movieName);
					
					commonMgr.createEntity(movie);
				}
			} else if(StringUtil.isNullOrEmpty(movieName)) {
				errMsg = getText("movie.name.can.not.empty");
			} else if(catId != null) {
				errMsg = getText("movie.category.can.not.empty");
			} else if(StringUtil.isNullOrEmpty(description)) {
				errMsg = getText("movie.description.can.not.empty");
			} else if(StringUtil.isNullOrEmpty(detail)) {
				errMsg = getText("movie.detail.can.not.empty");
			} else if(imageFeature != null) {
				errMsg = getText("movie.image.feature.can.not.empty");
			} else if(movieFile != null) {
				errMsg = getText("movie.file.can.not.empty");
			}
		}
		
		listCat = commonMgr.getListCategory();
		
		listMovie = commonMgr.getListMovies(null, null);
		
		return SUCCESS;
	}
	
	public String deleteCat() throws Exception {
		if(session.get("admin") == null) {
			return "login";
		}
		
		if(catId != null) {
			Category category = commonMgr.getEntity(Category.class, catId);
			if(category != null) {
				commonMgr.deleteEntity(category);
			}
		}
		return SUCCESS;
	}
	
	public String deleteMovie() throws Exception {
		if(session.get("admin") == null) {
			return "login";
		}
		
		if(id != null) {
			Movie movie = commonMgr.getEntity(Movie.class, id);
			if(movie != null) {
				File file = new File(movie.getRealPath());
				if(file.exists()) {
					file.delete();
				}
				commonMgr.deleteEntity(movie);
			}
		}
		
		return SUCCESS;
	}
	
	public String watchMovie() throws Exception {
		movie = commonMgr.getEntity(Movie.class, id);
		movie.loadListRelateMovie(commonMgr);
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public List<Category> getListCat() {
		return listCat;
	}

	public void setListCat(List<Category> listCat) {
		this.listCat = listCat;
	}

	public List<Movie> getListMovie() {
		return listMovie;
	}

	public void setListMovie(List<Movie> listMovie) {
		this.listMovie = listMovie;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
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

	public File getImageFeature() {
		return imageFeature;
	}

	public void setImageFeature(File imageFeature) {
		this.imageFeature = imageFeature;
	}

	public String getImageFeatureFileName() {
		return imageFeatureFileName;
	}

	public void setImageFeatureFileName(String imageFeatureFileName) {
		this.imageFeatureFileName = imageFeatureFileName;
	}

	public String getImageFeatureContentType() {
		return imageFeatureContentType;
	}

	public void setImageFeatureContentType(String imageFeatureContentType) {
		this.imageFeatureContentType = imageFeatureContentType;
	}

	public File getMovieFile() {
		return movieFile;
	}

	public void setMovieFile(File movieFile) {
		this.movieFile = movieFile;
	}

	public String getMovieFileFileName() {
		return movieFileFileName;
	}

	public void setMovieFileFileName(String movieFileFileName) {
		this.movieFileFileName = movieFileFileName;
	}

	public String getMovieFileContentType() {
		return movieFileContentType;
	}

	public void setMovieFileContentType(String movieFileContentType) {
		this.movieFileContentType = movieFileContentType;
	}

	public File getMyFileUpload() {
		return myFileUpload;
	}

	public void setMyFileUpload(File myFileUpload) {
		this.myFileUpload = myFileUpload;
	}

	public String getMyFileUploadFileName() {
		return myFileUploadFileName;
	}

	public void setMyFileUploadFileName(String myFileUploadFileName) {
		this.myFileUploadFileName = myFileUploadFileName;
	}

	public String getMyFileUploadContentType() {
		return myFileUploadContentType;
	}

	public void setMyFileUploadContentType(String myFileUploadContentType) {
		this.myFileUploadContentType = myFileUploadContentType;
	}

	public List<Category> getListCatMenu() {
		return listCatMenu;
	}

	public void setListCatMenu(List<Category> listCatMenu) {
		this.listCatMenu = listCatMenu;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
