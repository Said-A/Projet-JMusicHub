package main.java.model;
import main.java.controlleur.*;

import java.lang.Exception;

public class NoAlbumFoundException extends Exception {

	public NoAlbumFoundException (String msg) {
		super(msg);		
	}
}