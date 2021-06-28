package model;
import controller.*;

import java.lang.Exception;

public class NoPlayListFoundException extends Exception {

	public NoPlayListFoundException (String msg) {
		super(msg);
	}
}