package main.java.model;
import main.java.controlleur.*;

import java.lang.Exception;

public class NoElementFoundException extends Exception {

	public NoElementFoundException (String msg) {
		super(msg);
	}
}