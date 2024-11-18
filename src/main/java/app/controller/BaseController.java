package app.controller;

import app.data.Library;
import app.service.LibraryApiService;

@SuppressWarnings("exports")
public abstract class BaseController {
    
    protected Library library;
    protected LibraryApiService libraryApiService;

    public void setLibrary(Library library) {
        this.library = library;
    }
    
    public void setLibraryApiService(LibraryApiService libraryApiService) {
        this.libraryApiService = libraryApiService;
    }
    
    public void setLibAndService(Library library, LibraryApiService libraryApiService ) {
    	this.library = library;
    	this.libraryApiService = libraryApiService;
    }
}