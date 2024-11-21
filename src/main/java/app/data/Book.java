package app.data;

import java.util.List;

public class Book {

	public class VolumeInfo {
		public class ImageLinks {
			public String thumbnail;
		}

		public String title;
		public List<String> authors;
		public String publisher;
		public String publishedDate;
		public String description;
		public String categories;
		public int pageCount;
		public ImageLinks imageLinks;
		public String previewLink;
		public String infoLink;
	}

	public class AccesInfo {
		public String webReaderLink;
	}
	
	public String id;
	public VolumeInfo volumeInfo;
	public AccesInfo accesInfo;
}