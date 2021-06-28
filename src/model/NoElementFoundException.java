package model;
import controller.*;

import java.lang.Exception;

public class NoElementFoundException extends Exception {

	public NoElementFoundException (String msg) {
		super(msg);
	}
}