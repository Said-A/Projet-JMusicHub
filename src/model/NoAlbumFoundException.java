package model;
import controller.*;

import java.lang.Exception;

public class NoAlbumFoundException extends Exception {

	public NoAlbumFoundException (String msg) {
		super(msg);		
	}
}