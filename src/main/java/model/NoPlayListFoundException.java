package main.java.model;
import main.java.controlleur.*;

import java.lang.Exception;

public class NoPlayListFoundException extends Exception {

	public NoPlayListFoundException (String msg) {
		super(msg);
	}
}